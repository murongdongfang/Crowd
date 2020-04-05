package com.whpu.crowd;

import com.aliyun.api.gateway.demo.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 *@author xxh
 *@date 2020/3/28
 *@discription: crowdfunding07-member-parent
 */
@SpringBootTest(classes = {App_Authentication_9003.class})
@RunWith(SpringRunner.class)
public class SendMsgTest {
  private Logger logger = LoggerFactory.getLogger(SendMsgTest.class);
  @Test
  public void testSend(){
    String host = "https://cxkjsms.market.alicloudapi.com";
    String path = "/chuangxinsms/dxjk";
    String method = "POST";
    String appcode = "3c5cda341fe94f2bbd68b40ca0867142";
    Map<String, String> headers = new HashMap<String, String>();
    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
    headers.put("Authorization", "APPCODE " + appcode);
    Map<String, String> querys = new HashMap<String, String>();
    //测试可用默认短信模板,测试模板为专用模板不可修改,如需自定义短信内容或改动任意字符,请联系旺旺或QQ726980650进行申请
    querys.put("content", "【创信】你的验证码是：5873，3分钟内有效！");
    querys.put("mobile", "15927468231");
    Map<String, String> bodys = new HashMap<String, String>();


    try {
      /**
       * 重要提示如下:
       * HttpUtils请从
       * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
       * 下载
       *
       * 相应的依赖请参照
       * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
       */
      HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
      System.out.println("===============>"+response.toString());
      //获取response的body
      //System.out.println(EntityUtils.toString(response.getEntity()));
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
  @Test
  public void testSendMsg(){
    String host = "http://ihuyijksms.market.alicloudapi.com";
    String path = "/sms/l70";
    String method = "POST";
    String appcode = "3c5cda341fe94f2bbd68b40ca0867142";
    Map<String, String> headers = new HashMap<String, String>();
    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
    headers.put("Authorization", "APPCODE " + appcode);
    Map<String, String> querys = new HashMap<String, String>();
    querys.put("content", "content");
    querys.put("mobile", "15927468231");
    querys.put("sign", "sign");
    Map<String, String> bodys = new HashMap<String, String>();


    try {
      /**
       * 重要提示如下:
       * HttpUtils请从
       * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
       * 下载
       *
       * 相应的依赖请参照
       * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
       */
      HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
      logger.error(response.toString());
      System.out.println("==========>"+response.toString());
      //获取response的body
      //System.out.println(EntityUtils.toString(response.getEntity()));
    } catch (Exception e) {
      e.printStackTrace();
    }
      
  }
}
