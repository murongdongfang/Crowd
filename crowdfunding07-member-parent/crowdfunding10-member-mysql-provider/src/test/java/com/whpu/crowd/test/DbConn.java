package com.whpu.crowd.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.whpu.crowd.entity.po.MemberPO;
import com.whpu.crowd.mapper.MemberPOMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

/**
 *@author xxh
 *@date 2020/3/27
 *@discription: crowdfunding07-member-parent
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class DbConn {
  @Autowired
  private DruidDataSource druidDataSource;

  @Autowired
  private MemberPOMapper memberPOMapper;

  private Logger logger = LoggerFactory.getLogger(DbConn.class);
  @Test
  public void testMapper() {

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    String source = "123123";

    String encode = passwordEncoder.encode(source);

    MemberPO memberPO = new MemberPO(null, "jack", encode, "杰克", "jack@qq.com", 1, 1, "杰克", "123123", 2);

    memberPOMapper.insert(memberPO);
  }

  @Test
  public void testConn(){
    try {
      logger.info(String.valueOf(druidDataSource.getConnection()));
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
