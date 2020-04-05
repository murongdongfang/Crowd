package com.whpu.crowd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.UnknownHostException;

/**
 *@author xxh
 *@date 2020/3/28
 *@discription: crowdfunding07-member-parent
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/auth/member/to/reg/page").setViewName("member-reg");
    registry.addViewController("/auth/member/to/login/page").setViewName("member-login");
    registry.addViewController("/auth/member/to/center/page").setViewName("member-crowd");
    registry.addViewController("/member/my/crowd").setViewName("member-center");
  }

  @Bean("redisTemplate")
  public RedisTemplate<Object, Object> myRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
    RedisTemplate<Object, Object> template = new RedisTemplate();
    template.setConnectionFactory(redisConnectionFactory);
    template.setDefaultSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
    return template;
  }
}
