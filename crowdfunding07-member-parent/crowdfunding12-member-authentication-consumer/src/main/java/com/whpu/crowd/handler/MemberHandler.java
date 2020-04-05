package com.whpu.crowd.handler;

import com.whpu.crowd.api.MySqlRemoteService;
import com.whpu.crowd.api.RedisRemoteService;
import com.whpu.crowd.config.ShortMessageProperties;
import com.whpu.crowd.constant.CrowdConstant;
import com.whpu.crowd.entity.po.MemberPO;
import com.whpu.crowd.entity.vo.MemberLoginVO;
import com.whpu.crowd.entity.vo.MemberVO;
import com.whpu.crowd.util.CrowdUtil;
import com.whpu.crowd.util.ResultEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 *@author xxh
 *@date 2020/3/30
 *@discription: crowdfunding07-member-parent
 */

@Controller
public class MemberHandler {
  @Autowired
  private ShortMessageProperties shortMessageProperties;
  @Autowired
  private RedisRemoteService redisRemoteService;

  @Autowired
  private MySqlRemoteService mySqlRemoteService;
  @RequestMapping("/auth/member/logout")
  public String logout(HttpSession session) {

    session.invalidate();

    return "redirect:http://localhost/";
  }

  @RequestMapping("/auth/member/do/login")
  public String login(
    @RequestParam("loginacct") String loginacct,
    @RequestParam("userpswd") String userpswd,
    ModelMap modelMap,
    HttpSession session) {

    // 1.调用远程接口根据登录账号查询MemberPO对象
    ResultEntity<MemberPO> resultEntity =
      mySqlRemoteService.getMemberPOByLoginAcctRemote(loginacct);

    if(ResultEntity.FAILED.equals(resultEntity.getResult())) {

      modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, resultEntity.getMessage());

      return "member-login";

    }

    MemberPO memberPO = resultEntity.getData();

    if(memberPO == null) {
      modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_LOGIN_FAILED);

      return "member-login";
    }

    // 2.比较密码
    String userpswdDataBase = memberPO.getUserpswd();

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    boolean matcheResult = passwordEncoder.matches(userpswd, userpswdDataBase);

    if(!matcheResult) {
      modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_LOGIN_FAILED);

      return "member-login";
    }

    // 3.创建MemberLoginVO对象存入Session域
    MemberLoginVO memberLoginVO = new MemberLoginVO(memberPO.getId(), memberPO.getUsername(), memberPO.getEmail());
    session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_MEMBER, memberLoginVO);

    /**
     * 如果前面不加http就是普通的重定向，如果前面加上http就是重定向到网关，通过网关去访问
     *
     * 普通重定向的问题：重定向之后url地址http://localhost:9003/auth/member/to/center/page
     * 而当前的地址是在网关站点http://locahost/... 也就是说使用Session的时候Cookie存放网关这个站点地址是http://localhost/
     * 如果普通重定向导致Cookie在访问两个站点的时候无法共享，也就导致前端页面无法取出用户的session信息
     */
    return "redirect:http://localhost/auth/member/to/center/page";
  }

    @PostMapping("/auth/member/send/short/message.json")
    @ResponseBody
  public ResultEntity<String> sendMessage(@RequestParam("phoneNum") String phoneNum){
      //发送验证码到手机
      ResultEntity<String> res = CrowdUtil.sendCodeByShortMessage(
        shortMessageProperties.getHost(),
        shortMessageProperties.getPath(),
        shortMessageProperties.getMethod(),
        phoneNum,
        shortMessageProperties.getAppCode(),
        shortMessageProperties.getSign(),
        shortMessageProperties.getSkin()
      );
      if(ResultEntity.SUCCESS.equals(res.getResult())){

        //验证码
        String data = res.getData();
        String key = CrowdConstant.REDIS_CODE_PREFIX + phoneNum;
        ResultEntity<String> redisRes = redisRemoteService.setRedisKeyValueRemoteWithTimeOut(key, data, 60, TimeUnit.MINUTES);
        if(ResultEntity.SUCCESS.equals(redisRes.getResult())){
          return ResultEntity.successWithoutData();
        }else {
          return redisRes;
        }
      }

        return res;
    }


  @RequestMapping("/auth/do/member/register")
  public String register(MemberVO memberVO, ModelMap modelMap) {

    // 1.获取用户输入的手机号
    String phoneNum = memberVO.getPhoneNum();

    // 2.拼Redis中存储验证码的Key
    String key = CrowdConstant.REDIS_CODE_PREFIX + phoneNum;

    // 3.从Redis读取Key对应的value
    ResultEntity<String> resultEntity = redisRemoteService.getRedisStringValueByKeyRemote(key);

    // 4.检查查询操作是否有效
    String result = resultEntity.getResult();
    if(ResultEntity.FAILED.equals(result)) {

      modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, resultEntity.getMessage());

      return "member-reg";
    }

    String redisCode = resultEntity.getData();

    if(redisCode == null) {

      modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_CODE_NOT_EXISTS);

      return "member-reg";
    }

    // 5.如果从Redis能够查询到value则比较表单验证码和Redis验证码
    String formCode = memberVO.getCode();

    if(!Objects.equals(formCode, redisCode)) {

      modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_CODE_INVALID);

      return "member-reg";
    }

    // 6.如果验证码一致，则从Redis删除
    redisRemoteService.removeRedisKeyRemote(key);

    // 7.执行密码加密
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String userpswdBeforeEncode = memberVO.getUserpswd();

    String userpswdAfterEncode = passwordEncoder.encode(userpswdBeforeEncode);

    memberVO.setUserpswd(userpswdAfterEncode);

    // 8.执行保存
    // ①创建空的MemberPO对象
    MemberPO memberPO = new MemberPO();

    // ②复制属性
    BeanUtils.copyProperties(memberVO, memberPO);

    // ③调用远程方法
    ResultEntity<String> saveMemberResultEntity = mySqlRemoteService.saveMember(memberPO);

    if(ResultEntity.FAILED.equals(saveMemberResultEntity.getResult())) {

      modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, saveMemberResultEntity.getMessage());

      return "member-reg";
    }

    // 使用重定向避免刷新浏览器导致重新执行注册流程
    return "redirect:http://localhost/auth/member/to/login/page";
  }








}
