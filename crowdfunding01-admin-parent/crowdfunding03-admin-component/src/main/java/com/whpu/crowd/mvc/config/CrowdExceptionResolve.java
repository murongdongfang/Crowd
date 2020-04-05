package com.whpu.crowd.mvc.config;

import com.google.gson.Gson;
import com.whpu.crowd.constant.CrowdConstant;
import com.whpu.crowd.exception.AccessForbiddenException;
import com.whpu.crowd.exception.LoginAcctAlreadyInUseException;
import com.whpu.crowd.exception.LoginAcctAlreadyInUseForUpdateException;
import com.whpu.crowd.exception.LoginFailedException;
import com.whpu.crowd.util.CrowdUtil;
import com.whpu.crowd.util.ResultEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xxh
 * @date 2020/3/16
 * @discription: crowdfunding01-admin-parent
 */

@ControllerAdvice
public class CrowdExceptionResolve {

  /**
   * 登陆异常处理方法：密码或者账号错误
   * @param req  Http请求
   * @param res  Http响应
   * @param ex  自定义登陆失败异常
   * @return
   * @throws IOException
   */
  @ExceptionHandler(LoginFailedException.class)
  public ModelAndView resoveLoginFailedException(
    HttpServletRequest req, HttpServletResponse res, Exception ex) throws IOException{
    String viewName = "admin-login";
    ModelAndView mv = commonResolveException(req, res, ex, viewName);
    return mv;
  }

  /**
   * 登陆状态异常：未登陆就访问受保护资源
   */
  @ExceptionHandler(AccessForbiddenException.class)
  public ModelAndView resoveAccessForbindenExceptioin(
    HttpServletRequest req, HttpServletResponse res, Exception ex) throws IOException{
    String viewName = "admin-login";
    ModelAndView mv = commonResolveException(req, res, ex, viewName);
    return mv;
  }

  /**
   * 新增Admin时候用户名重复异常处理
   */

  @ExceptionHandler(LoginAcctAlreadyInUseException.class)
  public ModelAndView resoveLoginAcctAlreadyUse(
    HttpServletRequest req, HttpServletResponse res, Exception ex) throws IOException{
    String viewName = "admin-add";
    ModelAndView mv = commonResolveException(req, res, ex, viewName);
    return mv;
  }
  @ExceptionHandler(LoginAcctAlreadyInUseForUpdateException.class)
  public ModelAndView resoveUpdateLoginAcctInUse(
    HttpServletRequest req, HttpServletResponse res, Exception ex) throws IOException {

    String viewName = "system-error";
    ModelAndView mv = commonResolveException(req, res, ex, viewName);
    return mv;
  }
  /**
   * 异常处理公共代码部分
   */

  private ModelAndView commonResolveException(
    HttpServletRequest req,HttpServletResponse res,Exception ex,String viewName) throws IOException {
    boolean flag = CrowdUtil.judgeRequestType(req);
    if (flag) {
      String msg = ex.getMessage();
      ResultEntity<Object> failMsg = ResultEntity.failed(msg);
      Gson gson = new Gson();
      String json = gson.toJson(failMsg);
      res.getWriter().write(json);
      return null;
    }
    ModelAndView mv = new ModelAndView();
    mv.setViewName(viewName);
    mv.addObject(CrowdConstant.ATTR_NAME_EXCEPTION, ex);
    return mv;

  }
}
