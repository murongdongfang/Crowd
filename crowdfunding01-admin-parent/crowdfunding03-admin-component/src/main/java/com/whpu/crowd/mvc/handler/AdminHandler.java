package com.whpu.crowd.mvc.handler;

import com.github.pagehelper.PageInfo;
import com.whpu.crowd.constant.CrowdConstant;
import com.whpu.crowd.entity.Admin;
import com.whpu.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 *@author xxh
 *@date 2020/3/17
 *@discription: crowdfunding01-admin-parent
 */
@Controller
public class AdminHandler {

  @Autowired
  private AdminService adminService;

  @RequestMapping("/admin/do/logout.html")
  public String doLoginOut(HttpSession session){

    //清除session域中的会话数据，退出登陆
    session.invalidate();
    return "redirect:/admin/to/login/page.html";
  }

  @RequestMapping("/admin/do/login.html")
  public String doLogin(
    @RequestParam("loginAcct") String loginAcct, @RequestParam("userPswd") String userPswd, HttpSession session){
    Admin admin = adminService.getAdminByAcct(loginAcct, userPswd);
    session.setAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO,admin);
//    使用重定向防止表单重复提交，但是admin-main再/WEB-INF里边，所以需要配置controller跳转
    return "redirect:/admin/to/main/page.html";
  }

  /**
   * 分页显示用户
   * @param keyword
   * @param pageSize
   * @param pageNum
   * @param map
   * @return
   */
  @RequestMapping("/admin/get/page.html")
  public String getAdminPage(
    @RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "10") Integer pageSize,
    @RequestParam(defaultValue = "1") Integer pageNum, ModelMap map){

    PageInfo<Admin> adminPage = adminService.getAdminPage(keyword, pageNum, pageSize);
    map.put(CrowdConstant.ATTR_NAME_PAGE_INFO,adminPage);
    return "admin-page";
  }

  /**
   * 编辑用户信息,把数据库用户原来的信息回写到表单
   */
  @RequestMapping("/admin/to/edit/page.html")
  public String editAdmin(Integer adminId,Integer pageNum,String keyword,ModelMap map){

    Admin admin = adminService.getAdminById(adminId);
    map.addAttribute("admin",admin);
    return "admin-edit";
  }

  @RequestMapping("/admin/update.html")
  public String  updateAdmin(Integer pageNum,String keyword,Admin admin){
    adminService.updateAdmin(admin);
    return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
  }
  /**
   * 根据前台提交的表单新增用户
   * @param admin
   * @return
   */
  @RequestMapping("/admin/save.html")
  public String addAdmin(Admin admin){
    adminService.saveAdmin(admin);
    return "redirect:/admin/get/page.html?pageNum="+Integer.MAX_VALUE;
  }
  /**
   * 使用restful风格移除用户
   */
  @RequestMapping("/admin/remove/{adminId}/{pageNum}/{keyword}.html")
  public String removeAdmin (
    @PathVariable("adminId") Integer adminId,
    @PathVariable("pageNum") Integer pageNum,
    @PathVariable("keyword") String keyword){

    Integer remove = adminService.removeAdmin(adminId);
    System.out.println("删除"+remove);

    return "redirect:/admin/get/page.html?pageNum="+pageNum+"&keyword="+keyword;
  }
}
