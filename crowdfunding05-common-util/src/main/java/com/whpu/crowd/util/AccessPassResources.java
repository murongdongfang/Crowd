package com.whpu.crowd.util;

import com.whpu.crowd.constant.CrowdConstant;

import java.util.HashSet;
import java.util.Set;

/**
 *@author xxh
 *@date 2020/3/31
 *@discription: 用于网关中过滤请求的时候，放行静态资源
 */
public class AccessPassResources {
  public static final Set<String> PASS_RES_SET = new HashSet<>();
  static {
    PASS_RES_SET.add("/");
    PASS_RES_SET.add("/auth/member/to/reg/page");
    PASS_RES_SET.add("/auth/member/logout");
    PASS_RES_SET.add("/auth/member/do/login");
    PASS_RES_SET.add("/auth/do/member/register");
    PASS_RES_SET.add("/auth/member/send/short/message.json");
  }
  public static final Set<String> STATIC_RES_SET = new HashSet<>();
  static {
    STATIC_RES_SET.add("bootstrap");
    STATIC_RES_SET.add("css");
    STATIC_RES_SET.add("fonts");
    STATIC_RES_SET.add("img");
    STATIC_RES_SET.add("jquery");
    STATIC_RES_SET.add("layer");
    STATIC_RES_SET.add("script");
    STATIC_RES_SET.add("ztree");
  }

  /**
   * 判断当前传入的ServletPath是否包含需要放行的资源
   * @param servletPath
   * @return
   * true：需要放行
   * false：不能放行，必须要做登陆检查
   */
  public static boolean judgeCurrentServletPathIfStaticResource(String servletPath){
    if(servletPath == null || servletPath.length() == 0){
      throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
    }

    String[] paths = servletPath.split("/");
    /**
     * 传入的ServletPath：/aaa/bbb/ccc，第一个/经过拆分之后
     * 是空字符串，放入字符数组0号位置，所以一级路径应该是数组1号位置
     */
    String firstLevelPath = paths[1];

    return STATIC_RES_SET.contains(firstLevelPath);
  }
}
