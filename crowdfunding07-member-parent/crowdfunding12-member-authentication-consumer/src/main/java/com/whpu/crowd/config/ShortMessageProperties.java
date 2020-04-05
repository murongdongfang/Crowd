package com.whpu.crowd.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *@author xxh
 *@date 2020/3/30
 *@discription: crowdfunding07-member-parent
 */
@Component
@ConfigurationProperties(prefix = "short.message")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShortMessageProperties {
  private String host;
  private String path;
  private String method;
  private String appCode;
  private String sign;
  private String skin;
}
