package com.whpu.crowd.service.api;

import com.whpu.crowd.entity.Auth;

import java.util.List;
import java.util.Map;

/**
 *@author xxh
 *@date 2020/3/22
 *@discription: crowdfunding01-admin-parent
 */

public interface AuthService {
  List<Integer> getAssignedAuthIdByRoleId(Integer roledId);

  Integer saveRoleAuthRelationship(Map<String, List<Integer>> map);

  List<Auth> getAll();
}
