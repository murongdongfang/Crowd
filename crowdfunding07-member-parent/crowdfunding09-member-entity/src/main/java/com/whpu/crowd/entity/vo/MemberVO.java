package com.whpu.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *@author xxh
 *@date 2020/3/30
 *@discription: crowdfunding07-member-parent
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {

  private static long serialVersionUID = 1L;
  private String loginacct;
  private String userpswd;
  private String username;
  private String email;
  private String phoneNum;
  private String code;
  
  
  
  
}
