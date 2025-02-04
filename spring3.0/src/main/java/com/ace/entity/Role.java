package com.ace.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Role extends BaseEntity {
    private String name;

    @ManyToMany(mappedBy = Role_.ID) // 指向 `User` 端的 `roles` 屬性
    private List<UserProfile> users = new ArrayList<>();
}
