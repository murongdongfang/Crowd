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
 *@date 2020/4/1
 *@discription: crowdfunding07-member-parent
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/agree/protocol/page").setViewName("project-agree");
    registry.addViewController("/launch/project/page").setViewName("project-launch");
    registry.addViewController("/return/info/page").setViewName("project-return");
    registry.addViewController("/create/confirm/page").setViewName("project-confirm");
    registry.addViewController("/create/success").setViewName("project-success");
    registry.addViewController("/show/detail").setViewName("project-detail");
  }
  @Bean("redisTemplate")
  public RedisTemplate<Object, Object> myRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
    RedisTemplate<Object, Object> template = new RedisTemplate();
    template.setConnectionFactory(redisConnectionFactory);
    template.setDefaultSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
    return template;
  }
}
