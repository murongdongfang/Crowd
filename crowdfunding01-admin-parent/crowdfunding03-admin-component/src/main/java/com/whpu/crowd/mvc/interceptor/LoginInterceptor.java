package com.whpu.crowd.mvc.interceptor;

import com.whpu.crowd.constant.CrowdConstant;
import com.whpu.crowd.exception.AccessForbiddenException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *@author xxh
 *@date 2020/3/17
 *@discription: 登陆状态检查，要想访问受保护的资源必须要进行登陆状态检查
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    HttpSession session = request.getSession();
    Object login = session.getAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO);

    if (login == null) {
      throw new AccessForbiddenException(CrowdConstant.MESSAGE_ACCESS_FORBIDEN);
    }
    return true;
  }


}
