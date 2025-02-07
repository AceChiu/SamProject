package com.ace.request;

import lombok.Data;

/**
 * @author: ACE.CHIU
 * @create: 2023-03-19
 */
@Data
public class TeacherDtoRequest {
  private String username;

  private String googleId;
  private String email;
  private String uuid;
  private String imgUrl;
  private String skill;
  private String experience;
}
