package com.whpu.crowd.service.impl;

import com.whpu.crowd.entity.Menu;
import com.whpu.crowd.mapper.MenuMapper;
import com.whpu.crowd.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *@author xxh
 *@date 2020/3/18
 *@discription: crowdfunding01-admin-parent
 */
@Service
public class MenuServiceImpl implements MenuService {
  @Autowired
  private MenuMapper menuMapper;

  @Override
  public List<Menu> getAll() {
    return menuMapper.selectByExample(null);
  }

  @Override
  public Integer saveMenu(Menu menu) {
    return menuMapper.insertSelective(menu);
  }

  @Override
  public Integer updateMenu(Menu menu) {
    return menuMapper.updateByPrimaryKeySelective(menu);
  }

  @Override
  public Integer removeMenu(Integer id) {
    return menuMapper.deleteByPrimaryKey(id);
  }
}
