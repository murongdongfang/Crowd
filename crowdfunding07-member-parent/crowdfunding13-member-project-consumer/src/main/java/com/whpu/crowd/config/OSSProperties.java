package com.whpu.crowd.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *@author xxh
 *@date 2020/3/31
 *@discription: crowdfunding07-member-parent
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class OSSProperties {
  private String endPoint;
  private String bucketName;
  private String accessKeyId;
  private String accessKeySecret;
  private String bucketDomain;
}
