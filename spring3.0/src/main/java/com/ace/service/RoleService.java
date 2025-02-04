package com.ace.service;

import com.ace.entity.Role;
import com.ace.entity.UserProfile;
import com.ace.exception.BusinessException;
import com.ace.repository.BasicJpaRepository;
import com.ace.repository.RoleRepository;
import com.ace.request.RoleDtoRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * @author: ACE.CHIU
 * @create: 2025-02-04
 */

public class RoleService extends BasicService<Role> {
    @Autowired
    private RoleRepository repository;

    @Autowired
    private UserProfileService userProfileService;

    @Override
    public BasicJpaRepository<Role> getRepository() {
        return repository;
    }

    public Role create(RoleDtoRequest request) {
        Optional<Role> roleOpt = repository.findByName(request.getName());
        if (roleOpt.isPresent()) {
            throw new BusinessException("This Role is existed, Name: " + request.getName());
        }
        Role role = new Role();
        role.setName(request.getName());
        request.getUsernames().forEach(username -> {
            UserProfile userProfile = userProfileService.findByUsername(username);
            if (userProfile != null) {
                role.getUsers().add(userProfile);
            }
        });
        return repository.save(role);
    }

    public Role update(RoleDtoRequest request) {
        Optional<Role> roleOpt = repository.findByName(request.getName());
        if (roleOpt.isPresent()) {
            throw new BusinessException("This Role is existed, Name: " + request.getName());
        }
        Role role = new Role();
        role.setName(request.getName());
        request.getUsernames().forEach(username -> {
            UserProfile userProfile = userProfileService.findByUsername(username);
            if (userProfile != null) {
                role.getUsers().add(userProfile);
            }
        });
        return repository.save(role);
    }

    public List<Role> getRoles() {
        return repository.findAll();
    }
}
