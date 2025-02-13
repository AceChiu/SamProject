package com.ace.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.List;


/**
 * @author: ACE.CHIU
 * @create: 2023-03-19
 */
@Entity
@Data
public class Student extends BaseEntity {

    private static final long serialVersionUID = 1651345380729541023L;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    private UserProfile userProfile;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Course> courses;

    private String school;

    private String guardianName;

    private String guardianPhone;

    private String guardianEmail;

    private Region region;
}
