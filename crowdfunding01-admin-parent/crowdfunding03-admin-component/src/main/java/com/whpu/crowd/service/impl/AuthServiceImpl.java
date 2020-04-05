package com.whpu.crowd.service.impl;

import com.whpu.crowd.entity.Auth;
import com.whpu.crowd.entity.AuthExample;
import com.whpu.crowd.mapper.AuthMapper;
import com.whpu.crowd.service.api.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *@author xxh
 *@date 2020/3/22
 *@discription: crowdfunding01-admin-parent
 */

@Service
public class AuthServiceImpl implements AuthService {
  @Autowired
  private AuthMapper authMapper;

  @Override
  public List<Integer> getAssignedAuthIdByRoleId(Integer roledId) {
    return authMapper.selectAssignedAuthIdByRoleId(roledId);
  }

  @Override
  public Integer saveRoleAuthRelationship(Map<String, List<Integer>> map) {
    List<Integer> roleIdList = map.get("roleId");
    Integer roleId = roleIdList.get(0);
    authMapper.deleteOldRelationShip(roleId);
    List<Integer> authIdList = map.get("authIdArray");
    if(authIdList != null && authIdList.size() > 0){
      Integer res = authMapper.insertNewRelationShip(roleId, authIdList);
      return res;
    }
    return -1;
  }

  @Override
  public List<Auth> getAll() {
    return authMapper.selectByExample(new AuthExample());
  }
}
