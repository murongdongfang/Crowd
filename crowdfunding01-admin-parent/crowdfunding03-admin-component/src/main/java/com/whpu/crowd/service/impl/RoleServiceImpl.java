package com.whpu.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whpu.crowd.entity.Role;
import com.whpu.crowd.entity.RoleExample;
import com.whpu.crowd.mapper.RoleMapper;
import com.whpu.crowd.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *@author xxh
 *@date 2020/3/18
 *@discription: crowdfunding01-admin-parent
 */

@Service
public class RoleServiceImpl implements RoleService {
  @Autowired
  private RoleMapper roleMapper;
  @Override
  public PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword) {
    PageHelper.startPage(pageNum,pageSize);
    List<Role> roles = roleMapper.selectRoleByKeyword(keyword);
    return new PageInfo<>(roles);
  }

  @Override
  public Integer saveRole(Role role) {
    return roleMapper.insert(role);
  }

  @Override
  public Integer editRole(Role role) {
    return roleMapper.updateByPrimaryKeySelective(role);
  }

  @Override
  public Integer removeRole(List<Integer> roleIdList) {
    RoleExample roleExample = new RoleExample();
    RoleExample.Criteria criteria = roleExample.createCriteria().andIdIn(roleIdList);
    int res = roleMapper.deleteByExample(roleExample);
    return res;
  }

  @Override
  public List<Role> getAssignedRole(Integer adminId) {
    return roleMapper.selectAssignedRole(adminId);
  }

  @Override
  public List<Role> getUnAssignedRole(Integer adminId) {
    return roleMapper.selectUnAsignedRole(adminId);
  }
}
