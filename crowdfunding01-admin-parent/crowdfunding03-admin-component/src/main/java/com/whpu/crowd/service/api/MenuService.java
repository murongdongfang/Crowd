package com.whpu.crowd.service.api;

import com.whpu.crowd.entity.Menu;

import java.util.List;

/**
 *@author xxh
 *@date 2020/3/18
 *@discription: crowdfunding01-admin-parent
 */
public interface MenuService {

  public List<Menu> getAll();

  Integer saveMenu(Menu menu);

  Integer updateMenu(Menu menu);

  Integer removeMenu(Integer id);
}
