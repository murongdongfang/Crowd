package com.whpu.crowd.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.whpu.crowd.constant.CrowdConstant;
import com.whpu.crowd.util.AccessPassResources;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *@author xxh
 *@date 2020/3/31
 *@discription: 对所有通过网关的请求进行登陆检查，但是要放行静态资源等请求
 */

@Component
public class CrowdAccessFilter extends ZuulFilter {

  @Override
  public String filterType() {
    //在调用目标微服务之前执行请求过滤
    return "pre";
  }

  @Override
  public int filterOrder() {
    return 0;
  }

  @Override
  public boolean shouldFilter() {
    //在zuulFilter中获取请求通过ReqeustContext，原理ThreadLocal
    HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
    //调用的是getServletPath而非getPath
    String servletPath = request.getServletPath();
    if("/".equals(servletPath)){
      return false;
    }


    //ture过滤，false放行
    //judgeCurrentServletPathIfStaticResource:true表示这个请求不需要过滤，false这个请求需要过滤
    return !AccessPassResources.judgeCurrentServletPathIfStaticResource(servletPath);
  }

  /**
   * 如果当前请求允许放行，则需要判断当前请求是否已经登陆（Session域中是否包含登陆信息）
   * 如果已经登陆，直接放行。如果没有登陆则重定向回登陆页面（不能使用转发）
   * @return
   * @throws ZuulException
   */
  @Override
  public Object run() throws ZuulException {
    HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
    HttpServletResponse response = RequestContext.getCurrentContext().getResponse();
    HttpSession session = request.getSession();
    Object loginInfo = session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_MEMBER);

    if(loginInfo == null){
      try {
        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_MEMBER,CrowdConstant.MESSAGE_ACCESS_FORBIDEN);
        response.sendRedirect("/auth/member/to/login/page");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    // 当前实现会忽略这个方法的返回值，所以返回null，不做特殊处理
    return null;
  }
}
