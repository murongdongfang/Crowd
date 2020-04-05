package com.whpu;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whpu.crowd.entity.Admin;
import com.whpu.crowd.entity.Role;
import com.whpu.crowd.mapper.AdminMapper;
import com.whpu.crowd.mapper.RoleMapper;
import com.whpu.crowd.service.api.AdminService;
import com.whpu.crowd.util.CrowdUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * @author xxh
 * @date 2020/3/16
 * @discription: crowdfunding01-admin-parent
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})
public class TestConnect {


    @Autowired
    private DataSource dataSource;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void testInsert(){
        for (int i = 0 ; i < 235; i++) {
            Role role = new Role("aaa" + i);
            roleMapper.insert(role);
        }
    }
    @Test
    public void testService(){
        Admin admin = new Admin(7, "user", "2223434", "jerry", "23434@163.com", "2020/3/16/14:00");
        int i = adminService.saveAdmin(admin);
        System.out.println(i);
    }

    @Test
    public void testMD5(){
        System.out.println(CrowdUtil.md5("yyy"));
        PageHelper.startPage(1,5);
        List<Admin> admins = adminMapper.selectAdminListByKeyword("xxx");
        PageInfo<Admin> pageInfo = new PageInfo<>(admins);
        System.out.println("pageInfo============>"+pageInfo);
        System.out.println("=============>"+pageInfo.getList() == null);
    }

    @Test
    public void testServiceSelect(){
        List<Admin> all = adminService.getAll();
        System.out.println(all);

    }

    @Test
    public void testConnect(){
        try {
            System.out.println(dataSource.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelect(){
        Admin admin = adminMapper.selectByPrimaryKey(2);
        List<Admin> list = adminMapper.selectByExample(null);

        System.out.println(list);
    }
}
