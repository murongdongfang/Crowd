package com.whpu.crowd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

/**
 *@author xxh
 *@date 2020/3/31
 *@discription: crowdfunding07-member-parent
 */

@Configuration
public class RedisConfig {

  @Bean("redisTemplate")
  public RedisTemplate<Object, Object> myRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
    RedisTemplate<Object, Object> template = new RedisTemplate();
    template.setConnectionFactory(redisConnectionFactory);
    template.setDefaultSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
    return template;
  }
}
