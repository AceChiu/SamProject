package com.ace.entity;

import com.ace.common.ClarifyEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.List;

/**
 * @author: ACE.CHIU
 * @create: 2022-07-03
 */
@Data
@Entity
public class Teacher extends BaseEntity {
    private static final long serialVersionUID = -66606257433452604L;

    // setting join column don't create middle table
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private UserProfile userProfile;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = Course_.TEACHER)
    private List<Course> courses;

    private String imgUrl;

    private String selfIntroduction;

    private String partOfCompany;

    private String suitAgeFrom;

    private String suitAgeTo;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Label> label;

    /*
     *  尚未認證，測試中，驗證成功
     *  測試中可以開課，但只能開體驗課
     *  驗證成功，所有客都可以開
     */

    private ClarifyEnum clarify;
}
