package com.ace.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ACE.CHIU
 * @create: 2023-10-24
 */
@Entity
public class Label extends BaseEntity {
    @Getter
    @Setter
    private String labelName;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = Teacher_.LABEL)
    private List<Teacher> teachers = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = Course_.LABEL)
    private List<Course> courses = new ArrayList<>();
}
