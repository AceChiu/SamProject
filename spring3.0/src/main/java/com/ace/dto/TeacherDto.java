package com.ace.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: ACE.CHIU
 * @create: 2023-03-23
 */
@Data
@Builder
public class TeacherDto {
  private long id;
  private String imgUrl;
  private String skill;
  private String experience;
}
