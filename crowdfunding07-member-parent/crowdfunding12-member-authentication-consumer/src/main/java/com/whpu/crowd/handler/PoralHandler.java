package com.whpu.crowd.handler;

import com.whpu.crowd.api.MySqlRemoteService;
import com.whpu.crowd.constant.CrowdConstant;
import com.whpu.crowd.entity.vo.PortalTypeVO;
import com.whpu.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 *@author xxh
 *@date 2020/3/28
 *@discription: crowdfunding07-member-parent
 */

@Controller
public class PoralHandler {


  @Autowired
  private MySqlRemoteService mySQLRemoteService;

  @RequestMapping("/")
  public String shwoPoratalPage(Model model) {
    // 1、调用MySQLRemoteService提供的方法查询首页要显示的数据
    ResultEntity<List<PortalTypeVO>> resultEntity =
      mySQLRemoteService.getPortalTypeProjectDataRemote();

    // 2.检查查询结果
    String result = resultEntity.getResult();
    if (ResultEntity.SUCCESS.equals(result)) {

      // 3.获取查询结果数据
      List<PortalTypeVO> list = resultEntity.getData();

      // 4.存入模型
      model.addAttribute(CrowdConstant.ATTR_NAME_PORTAL_DATA, list);

    }
    return "portal";
  }
}