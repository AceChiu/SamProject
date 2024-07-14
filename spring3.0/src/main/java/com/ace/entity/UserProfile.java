package com.ace.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * @author: ACE.CHIU
 * @create: 2022-06-30
 */
@Entity
public class UserProfile extends BaseEntity {

    private static final long serialVersionUID = 3138912709489043965L;

    @Getter
    @Setter
    private String encodeGoogleId;

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

    @Getter
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();
}
