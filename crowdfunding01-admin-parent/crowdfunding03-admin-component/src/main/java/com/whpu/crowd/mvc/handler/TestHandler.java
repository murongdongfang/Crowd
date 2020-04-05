package com.whpu.crowd.mvc.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whpu.crowd.entity.Admin;
import com.whpu.crowd.entity.Student;
import com.whpu.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author xxh
 * @date 2020/3/16
 * @discription: crowdfunding01-admin-parent
 */

@Controller
public class TestHandler {

    @Autowired
    private AdminService adminService;
    @RequestMapping("/success.html")
    public String success(ModelMap admins){

        String str = null;
        str.trim();
        return "success";
    }


    @RequestMapping("/send/json.html")
    @ResponseBody
    public String handlerJson(@RequestBody Student student){
        List<Admin> all = adminService.getAll();
        System.out.println(student);
        ObjectMapper om = new ObjectMapper();

        return all.toString();
    }
}
