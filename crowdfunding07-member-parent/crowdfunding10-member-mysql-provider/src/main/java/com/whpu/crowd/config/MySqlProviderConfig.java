package com.whpu.crowd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 *@author xxh
 *@date 2020/3/28
 *@discription: crowdfunding07-member-parent
 */

@Configuration
@EnableTransactionManagement
public class MySqlProviderConfig {

  //注册事务管理器在容器中
  @Bean
  public PlatformTransactionManager transactionManager(DataSource dataSource) throws Exception{
    return new DataSourceTransactionManager(dataSource);
  }
}
