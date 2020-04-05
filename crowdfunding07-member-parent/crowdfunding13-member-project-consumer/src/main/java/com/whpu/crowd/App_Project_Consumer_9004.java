package com.whpu.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *@author xxh
 *@date 2020/3/31
 *@discription: crowdfunding07-member-parent
 */

@SpringBootApplication
@EnableFeignClients
public class App_Project_Consumer_9004 {
  public static void main(String[] args) {
    SpringApplication.run(App_Project_Consumer_9004.class,args);
  }
}
