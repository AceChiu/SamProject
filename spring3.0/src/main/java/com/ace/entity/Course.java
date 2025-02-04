package com.ace.entity;

import com.ace.common.CourseTypeEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author: ACE.CHIU
 * @create: 2022-07-03
 */
@Entity
@Data
public class Course extends BaseEntity {

  private static final long serialVersionUID = -5103435175697067139L;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = Teacher_.ID, unique = true, nullable = false)
  private Teacher teacher;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = Student_.COURSES)
  private List<Student> students;

  private String imgUrl; 

  private String content;

  private int studentLimit;

  private String address;

  private CourseTypeEnum courseType;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Label> label;

  private BigDecimal money;
}
