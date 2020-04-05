package com.whpu.crowd.service.api;

import com.github.pagehelper.PageInfo;
import com.whpu.crowd.entity.Role;

import java.util.List;

/**
 *@author xxh
 *@date 2020/3/18
 *@discription: crowdfunding01-admin-parent
 */
public interface RoleService {

  public PageInfo<Role> getPageInfo (Integer pageNum, Integer pageSize, String keyword);

  Integer saveRole(Role role);

  Integer editRole(Role role);

  Integer removeRole(List<Integer> roleIdList);

  List<Role> getAssignedRole(Integer adminId);

  List<Role> getUnAssignedRole(Integer adminId);
}
