package com.whpu.crowd.mvc.handler;

import com.whpu.crowd.entity.Menu;
import com.whpu.crowd.service.api.MenuService;
import com.whpu.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@author xxh
 *@date 2020/3/18
 *@discription: crowdfunding01-admin-parent
 */

@Controller
public class MenuHandler {

  @Autowired
  private MenuService menuService;

  @ResponseBody
  @RequestMapping("/menu/remove.json")
  public ResultEntity<String> removeMenu(@RequestParam("id") Integer id) {

    menuService.removeMenu(id);

    return ResultEntity.successWithoutData();
  }

  @ResponseBody
  @RequestMapping("/menu/update.json")
  public ResultEntity<String> updateMenu(Menu menu) {

    menuService.updateMenu(menu);

    return ResultEntity.successWithoutData();
  }


  @ResponseBody
  @RequestMapping("/menu/save.json")
  public ResultEntity<String> saveMenu(Menu menu) {


    menuService.saveMenu(menu);

    return ResultEntity.successWithoutData();
  }

  @ResponseBody
  @RequestMapping("/menu/get/whole/tree.json")
  public ResultEntity<Menu> getWholeMenu(){
    List<Menu> allMenu = menuService.getAll();
    Menu root = null;
    Map<Integer,Menu> map = new HashMap<>();
    for (Menu menu : allMenu) {
        map.put(menu.getId(),menu);
    }
    for (Menu menu : allMenu) {
      Integer menuPid = menu.getPid();
      //根节点
      if(menuPid == null){
        root = menu;
        continue;
      }
      //获取当前节点的父节点
      Menu father = map.get(menuPid);
      father.getChildren().add(menu);

    }

    return ResultEntity.successWithData(root);
  }
}
