package com.ace.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;


/**
 * @author: ACE.CHIU
 * @create: 2022-06-30
 */
@Entity
public class UserProfile extends BaseEntity {

  private static final long serialVersionUID = 3138912709489043965L;
  
  @Getter
  @Setter
  private String googleId;
  
  @Getter
  @Setter
  private String lindId;
  
  @Getter
  @Setter
  private String igId;

  @Getter
  @Setter
  private String familyName;
  
  @Getter
  @Setter
  private String givenName;

  @Getter
  @Setter
  private String name;
  
  @Getter
  @Setter
  private String birthday;
  
  @Getter
  @Setter
  private String email;
  
  @Getter
  @Setter
  private String phone;
  
  @Getter
  @Setter
  private String address;
  
  @Getter
  @Setter
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = Student_.USER_PROFILE)
  private Student student;
  
  @Getter
  @Setter
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = Teacher_.USER_PROFILE)
  private Teacher teacher;
}
