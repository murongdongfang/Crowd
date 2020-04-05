package com.whpu.crowd.service.api;

import com.github.pagehelper.PageInfo;
import com.whpu.crowd.entity.Admin;

import java.util.List;

/**
 * @author xxh
 * @date 2020/3/16
 * @discription: 针对后台管理员的增上改查操作
 */

public interface AdminService {
    public int saveAdmin(Admin admin);
    public List<Admin> getAll();

    public Admin getAdminByAcct(String loginAcct, String pswd);

    /**
     * 根据关键词进行查询，使用pageHelper分页查询
     * @param keyword 关键词
     * @param pageNum 页码
     * @param pageSize 每页词条数量
     * @return
     */
    public PageInfo<Admin> getAdminPage(String keyword, Integer pageNum, Integer pageSize);

    public Integer removeAdmin(Integer adminId);

    public Admin getAdminById(Integer adminId);

    public Integer updateAdmin(Admin admin);
    void saveAdminRoleRelationShip(Integer adminId,List<Integer> roleIdList);
}
