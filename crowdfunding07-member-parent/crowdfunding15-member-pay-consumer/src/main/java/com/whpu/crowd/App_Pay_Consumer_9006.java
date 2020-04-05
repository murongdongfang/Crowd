package com.whpu.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *@author xxh
 *@date 2020/4/5
 *@discription: crowdfunding07-member-parent
 */

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class App_Pay_Consumer_9006 {
  public static void main(String[] args) {
    SpringApplication.run(App_Pay_Consumer_9006.class,args);
  }
}
