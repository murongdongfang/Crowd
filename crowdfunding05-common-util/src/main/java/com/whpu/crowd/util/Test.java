package com.whpu.crowd.util;

import java.io.FileInputStream;

/**
 *@author xxh
 *@date 2020/4/2
 *@discription: crowdfunding05-common-util
 */
public class Test {
  public static void main(String[] args) throws Exception {
    String access_key_id = "LTAI4FfehcLYiEdqEC6nuc3U";
    String access_key_secret =  "zg4U4f3DDYwSb2BdBXRlrZdORcdLmm";
    //#https://xxh-crowd-project.oss-cn-beijing.aliyuncs.com/crowd/pic/JDK1.6.png
    String bucket_domain = "https://xxh-crowd-project.oss-cn-beijing.aliyuncs.com";
    String bucket_name =  "xxh-crowd-project";
    String end_point = "oss-cn-beijing.aliyuncs.com";  //bucket-domain的oss后面部分
    FileInputStream input = new FileInputStream("aaa.png");
    ResultEntity<String> res = CrowdUtil.uploadFileToOss(end_point, access_key_id, access_key_secret, input, bucket_name, bucket_domain, "aaa.png");
    System.out.println(res);

  }
}
