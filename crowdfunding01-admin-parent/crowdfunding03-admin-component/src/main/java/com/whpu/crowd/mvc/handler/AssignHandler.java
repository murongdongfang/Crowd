package com.whpu.crowd.mvc.handler;

import com.whpu.crowd.entity.Auth;
import com.whpu.crowd.entity.Role;
import com.whpu.crowd.service.api.AdminService;
import com.whpu.crowd.service.api.AuthService;
import com.whpu.crowd.service.api.RoleService;
import com.whpu.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 *@author xxh
 *@date 2020/3/22
 *@discription: crowdfunding01-admin-parent
 */


@Controller
public class AssignHandler {
  @Autowired
  private RoleService roleService;
  @Autowired
  private AdminService adminService;
  @Autowired
  private AuthService authService;
  @RequestMapping("/assign/to/assign/role/page.html")
  public String toAssignRolePage(Integer adminId, ModelMap map){
    List<Role> assignedRoleList = roleService.getAssignedRole(adminId);
    List<Role> unAssignedRoleList = roleService.getUnAssignedRole(adminId);
    map.addAttribute("assignedRoleList",assignedRoleList);
    map.addAttribute("unAssignedRoleList",unAssignedRoleList);
    return "assign-role";
  }

  @RequestMapping("/assign/do/role/assign.html")
  public String saveAdminRoleRelationShip(
    Integer adminId,Integer pageNum,String keyword,@RequestParam(required = false) List<Integer> roleIdList){
    adminService.saveAdminRoleRelationShip(adminId, roleIdList);

    return "redirect:/admin/get/page.html?pageNum"+pageNum+"&keyword="+keyword;
  }

  //  assign/get/assigned/auth/id/by/role/id.json
  @RequestMapping("/assign/get/assigned/auth/id/by/role/id.json")
  @ResponseBody
  public ResultEntity<List<Integer>> getAssignedAuthIdByRoleId(Integer roleId){
    List<Integer> authIdList = authService.getAssignedAuthIdByRoleId(roleId);
    return ResultEntity.successWithData(authIdList);
  }

  @ResponseBody
  @RequestMapping("/assign/do/role/assign/auth.json")
  public ResultEntity<String> saveRoleAuthRelationShip(
    @RequestBody Map<String,List<Integer>> map){
    Integer res = authService.saveRoleAuthRelationship(map);
    if(res < 0){
      return ResultEntity.failed("保存信息失败");
    }
    return  ResultEntity.successWithoutData();
  }

  @ResponseBody
  @RequestMapping("/assgin/get/all/auth.json")
  public ResultEntity<List<Auth>> getAllAuth(){
    List<Auth> authList = authService.getAll();
    return ResultEntity.successWithData(authList);
  }

}
