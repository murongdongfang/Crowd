package com.whpu.crowd.mvc.handler;

import com.whpu.crowd.service.api.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *@author xxh
 *@date 2020/3/22
 *@discription: crowdfunding01-admin-parent
 */
@Controller
public class AuthHandler {
  @Autowired
  private AuthService authService;

}
