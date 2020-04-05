package com.whpu.corwd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *@author xxh
 *@date 2020/3/28
 *@discription: crowdfunding07-member-parent
 */
@SpringBootApplication
@EnableEurekaClient
public class App_Redis_9002 {
  public static void main(String[] args) {
    SpringApplication.run(App_Redis_9002.class,args);
  }
}
