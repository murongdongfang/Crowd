package com.whpu.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *@author xxh
 *@date 2020/3/30
 *@discription: crowdfunding07-member-parent
 */


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginVO implements Serializable {

  private static long serialVersionUID = 1L;
  private Integer id;

  private String username;

  private String email;
}
