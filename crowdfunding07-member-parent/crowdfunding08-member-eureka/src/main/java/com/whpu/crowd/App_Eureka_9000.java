package com.whpu.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 *@author xxh
 *@date 2020/3/27
 *@discription: crowdfunding07-member-parent
 */
@SpringBootApplication
@EnableEurekaServer
public class App_Eureka_9000 {
  public static void main(String[] args) {
    SpringApplication.run(App_Eureka_9000.class,args);
  }
}
