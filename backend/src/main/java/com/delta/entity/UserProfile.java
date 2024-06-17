package com.delta.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


/**
 * @author: ACE.CHIU
 * @create: 2022-06-30
 */
@Entity
@ApiModel(value = "User Profile", description = "使用者資調")
public class UserProfile extends BaseEntity {

  private static final long serialVersionUID = 3138912709489043965L;
  
  @Getter
  @Setter
  @ApiModelProperty(value = "GOOGLE ID")
  private String googleId;
  
  @Getter
  @Setter
  @ApiModelProperty(value = "LINE ID")
  private String lindId;
  
  @Getter
  @Setter
  @ApiModelProperty(value = "IG ID")
  private String igId;

  @Getter
  @Setter
  @ApiModelProperty(value = "姓氏")
  private String familyName;
  
  @Getter
  @Setter
  @ApiModelProperty(value = "名子")
  private String givenName;

  @Getter
  @Setter
  @ApiModelProperty(value = "全名")
  private String name;
  
  @Getter
  @Setter
  @ApiModelProperty(value = "生日")
  private String birthday;
  
  @Getter
  @Setter
  @ApiModelProperty(value = "Email")
  private String email;
  
  @Getter
  @Setter
  @ApiModelProperty(value = "電話")
  private String phone;
  
  @Getter
  @Setter
  @ApiModelProperty(value = "連絡地址")
  private String address;
  
  @Getter
  @Setter
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @ApiModelProperty(value = "學生")
  private Student student;
  
  @Getter
  @Setter
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @ApiModelProperty(value = "教練")
  private Teacher teacher;
}
