package com.whpu.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 *@author xxh
 *@date 2020/3/28
 *@discription: crowdfunding07-member-parent
 */

@SpringBootApplication
@EnableZuulProxy
public class App_Zuul_80 {
  public static void main(String[] args) {
    SpringApplication.run(App_Zuul_80.class,args);
  }
}
