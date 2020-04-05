package com.whpu.crowd.mvc.handler;

import com.github.pagehelper.PageInfo;
import com.whpu.crowd.entity.Role;
import com.whpu.crowd.service.api.RoleService;
import com.whpu.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *@author xxh
 *@date 2020/3/18
 *@discription: crowdfunding01-admin-parent
 */

@Controller
public class RoleHandler {

  @Autowired
  private RoleService roleService;

  @RequestMapping("/role/get/page/info.json")
  @ResponseBody
  public ResultEntity<PageInfo<Role>> getPageInfo(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize,
                                                  @RequestParam(defaultValue = "") String keyword){
    PageInfo<Role> pageInfo = roleService.getPageInfo(pageNum, pageSize, keyword);

    return ResultEntity.successWithData(pageInfo);
  }

  @RequestMapping("role/save.json")
  @ResponseBody
  public ResultEntity<String> saveRole(Role role){
    Integer res = roleService.saveRole(role);
    return ResultEntity.successWithData(res.toString());
  }
  @RequestMapping("role/edit.json")
  @ResponseBody
  public ResultEntity<String> editRole(Role role){
    System.out.println(role);
    Integer res = roleService.editRole(role);
    if(res < 1){
      return ResultEntity.failed("更新失败");
    }
    return ResultEntity.successWithoutData();
  }

  @RequestMapping("/role/remove/by/role/id/array.json")
  @ResponseBody
  public ResultEntity<String> removeByRoleIdArray(@RequestBody List<Integer> roleIdList){
    Integer res = roleService.removeRole(roleIdList);
    if(res < 1){
      return ResultEntity.failed(res.toString());
    }

    return ResultEntity.successWithoutData();
  }


}
