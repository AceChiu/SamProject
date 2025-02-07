package com.ace.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author: ACE.CHIU
 * @create: 2022-06-30
 */
@Entity
@Data
public class UserProfile extends BaseEntity implements UserDetails {

    private static final long serialVersionUID = 3138912709489043965L;
    // email, lindId, facebookId... will be set to username, it's unique
    private String username;
    private String password;

    private String email;
    private String googleId;
    private String lindId;
    private String igId;

    private String name;
    private String familyName;
    private String givenName;

    private String birthday;
    private String phone;
    private String address;

    // setting mappedBy, user profile also could access student and teacher
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = Student_.USER_PROFILE)
    private Student student;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = Teacher_.USER_PROFILE)
    private Teacher teacher;

    // many to many will create a middle table, we use to default middle table
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
