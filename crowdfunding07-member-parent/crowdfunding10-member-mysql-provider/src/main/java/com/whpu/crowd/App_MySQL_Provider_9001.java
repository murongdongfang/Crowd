package com.whpu.crowd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *@author xxh
 *@date 2020/3/27
 *@discription: crowdfunding07-member-parent
 */

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.whpu.crowd.mapper")
public class App_MySQL_Provider_9001 {
  public static void main(String[] args) {
    SpringApplication.run(App_MySQL_Provider_9001.class,args);
  }
}
