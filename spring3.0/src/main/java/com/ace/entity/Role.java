package com.ace.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = UserProfile_.ROLES)
    private List<UserProfile> users = new ArrayList<>();
}
