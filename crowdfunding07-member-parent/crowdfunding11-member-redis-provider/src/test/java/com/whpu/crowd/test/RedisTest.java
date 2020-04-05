package com.whpu.crowd.test;

import com.whpu.corwd.App_Redis_9002;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *@author xxh
 *@date 2020/3/28
 *@discription: crowdfunding07-member-parent
 */

@SpringBootTest(classes = {App_Redis_9002.class})
@RunWith(SpringRunner.class)
public class RedisTest {
  
  @Autowired
  private StringRedisTemplate stringRedisTemplate;
  @Test
  public void testRedis(){
    Integer append = stringRedisTemplate.opsForValue().append("stu", "xxx");

  }

  @Test
  public void testEncode(){
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    System.out.println(passwordEncoder.encode("123"));

  }
}
