package com.whpu.crowd.api;

import com.whpu.crowd.entity.po.MemberPO;
import com.whpu.crowd.entity.vo.*;
import com.whpu.crowd.util.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 *@author xxh
 *@date 2020/3/28
 *@discription: crowdfunding07-member-parent
 */

@FeignClient("crowd-mysql-provider-9001")
public interface MySqlRemoteService {

  @RequestMapping("/get/project/detail/remote/{projectId}")
  public ResultEntity<DetailProjectVO> getDetailProjectVORemote(@PathVariable("projectId") Integer projectId);


  @RequestMapping("/get/portal/type/project/data/remote")
  public ResultEntity<List<PortalTypeVO>> getPortalTypeProjectDataRemote();

  @RequestMapping("/save/member/remote")
  public ResultEntity<String> saveMember(@RequestBody MemberPO memberPO);


  @RequestMapping("/get/memberpo/by/login/acct/remote")
  public ResultEntity<MemberPO> getMemberPOByLoginAcctRemote(@RequestParam("loginacct") String loginacct);

  @RequestMapping("/save/project/vo/remote")
  public ResultEntity<String> saveProjectVORemote(@RequestBody ProjectVO projectVO, @RequestParam("memberId") Integer memberId);

  @RequestMapping("/get/order/project/vo/remote")
  ResultEntity<OrderProjectVO> getOrderProjectVORemote(@RequestParam("projectId") Integer projectId, @RequestParam("returnId") Integer returnId);

  @RequestMapping("/get/address/vo/remote")
  ResultEntity<List<AddressVO>> getAddressVORemote(@RequestParam("memberId") Integer memberId);

  @RequestMapping("/save/address/remote")
  ResultEntity<String> saveAddressRemote(@RequestBody AddressVO addressVO);
  @RequestMapping("/save/order/remote")
  ResultEntity<String> saveOrderRemote(@RequestBody OrderVO orderVO);

}
