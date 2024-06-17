package com.ace.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: ACE.CHIU
 * @create: 2022-06-30
 */
@Getter
@Setter
public class UserProfileDto {
  
  private String familyName;

  private String givenName;

  private String name;
  
  private String birthday;
  
  private String email;
}
