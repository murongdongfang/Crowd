package com.whpu.crowd.handler;

import com.whpu.crowd.api.MySqlRemoteService;
import com.whpu.crowd.constant.CrowdConstant;
import com.whpu.crowd.entity.vo.AddressVO;
import com.whpu.crowd.entity.vo.MemberLoginVO;
import com.whpu.crowd.entity.vo.OrderProjectVO;
import com.whpu.crowd.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 *@author xxh
 *@date 2020/4/5
 *@discription: crowdfunding07-member-parent
 */
@Controller
public class OrderHandler {

  @Autowired
  private MySqlRemoteService mySQLRemoteService;

  private Logger logger = LoggerFactory.getLogger(OrderHandler.class);

  @RequestMapping("/save/address")
  public String saveAddress(AddressVO addressVO, HttpSession session) {

    // 1.执行地址信息的保存
    ResultEntity<String> resultEntity = mySQLRemoteService.saveAddressRemote(addressVO);

    logger.debug("地址保存处理结果："+resultEntity.getResult());

    // 2.从Session域获取orderProjectVO对象
    OrderProjectVO orderProjectVO = (OrderProjectVO) session.getAttribute("orderProjectVO");

    // 3.从orderProjectVO对象中获取returnCount
    Integer returnCount = orderProjectVO.getReturnCount();

    // 4.重定向到指定地址，重新进入确认订单页面
    return "redirect:http://localhost/order/confirm/order/"+returnCount;
  }

  @RequestMapping("/confirm/order/{returnCount}")
  public String showConfirmOrderInfo(
    @PathVariable("returnCount") Integer returnCount,
    HttpSession session) {

    // 1.把接收到的回报数量合并到Session域
    OrderProjectVO orderProjectVO = (OrderProjectVO) session.getAttribute("orderProjectVO");

    orderProjectVO.setReturnCount(returnCount);

    session.setAttribute("orderProjectVO", orderProjectVO);

    // 2.获取当前已登录用户的id
    MemberLoginVO memberLoginVO = (MemberLoginVO) session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_MEMBER);

    Integer memberId = memberLoginVO.getId();

    // 3.查询目前的收货地址数据
    ResultEntity<List<AddressVO>> resultEntity = mySQLRemoteService.getAddressVORemote(memberId);

    if(ResultEntity.SUCCESS.equals(resultEntity.getResult())) {
      List<AddressVO> list = resultEntity.getData();
      session.setAttribute("addressVOList", list);
    }

    return "confirm_order";
  }

  @RequestMapping("/confirm/return/info/{projectId}/{returnId}")
  public String showReturnConfirmInfo(
    @PathVariable("projectId") Integer projectId,
    @PathVariable("returnId") Integer returnId,
    HttpSession session) {

    ResultEntity<OrderProjectVO> resultEntity =
      mySQLRemoteService.getOrderProjectVORemote(projectId, returnId);

    if(ResultEntity.SUCCESS.equals(resultEntity.getResult())) {
      OrderProjectVO orderProjectVO = resultEntity.getData();

      // 为了能够在后续操作中保持orderProjectVO数据，存入Session域
      session.setAttribute("orderProjectVO", orderProjectVO);
    }
    System.out.println(resultEntity);

    return "confirm_return";
  }

}
