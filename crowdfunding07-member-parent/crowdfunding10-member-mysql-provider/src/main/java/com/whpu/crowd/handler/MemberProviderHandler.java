package com.whpu.crowd.handler;

import com.whpu.crowd.constant.CrowdConstant;
import com.whpu.crowd.entity.po.MemberPO;
import com.whpu.crowd.service.api.MemberService;
import com.whpu.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *@author xxh
 *@date 2020/3/28
 *@discription:
 */

@RestController
public class MemberProviderHandler {

  @Autowired
  private MemberService memberService;
  @RequestMapping("/get/memberpo/by/login/acct/remote")
  public ResultEntity<MemberPO> getMemberPoByLoginAcctRemote(@RequestParam("loginacct") String loginacct){
    MemberPO memberPO = memberService.getMemberPOByLoginAcct(loginacct);

    return ResultEntity.successWithData(memberPO);
  }

  @RequestMapping("/save/member/remote")
  public ResultEntity<String> saveMember(@RequestBody MemberPO memberPO){
    int i = 0;
    try {
      i = memberService.saveMember(memberPO);
    } catch (Exception e) {
      if(e instanceof DuplicateKeyException){
        return ResultEntity.failed(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
      }else {
        return ResultEntity.failed(e.getMessage());
      }
    }

    return ResultEntity.successWithData("成功保存"+i+"个用户");
  }
}
