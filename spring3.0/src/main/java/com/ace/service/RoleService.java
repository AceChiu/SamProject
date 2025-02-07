package com.ace.service;

import com.ace.entity.Role;
import com.ace.entity.UserProfile;
import com.ace.exception.BusinessException;
import com.ace.repository.BasicJpaRepository;
import com.ace.repository.RoleRepository;
import com.ace.request.RoleDtoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author: ACE.CHIU
 * @create: 2025-02-04
 */

@Service
public class RoleService extends BasicService<Role> {
    @Autowired
    private RoleRepository repository;

    @Autowired
    private UserProfileService userProfileService;

    @Override
    public BasicJpaRepository<Role> getRepository() {
        return repository;
    }

    public Role findByName(String name) {
        return repository.findByName(name.toUpperCase()).orElse(null);
    }

    public Role create(String name) {
        Optional<Role> roleOpt = repository.findByName(name.toUpperCase());
        if (roleOpt.isPresent()) {
            throw new BusinessException("This Role is existed, Name: " + name.toUpperCase());
        }
        Role role = new Role();
        role.setName(name.toUpperCase());
        return repository.save(role);
    }

    public void update(String name, RoleDtoRequest request) {
        Optional<Role> roleOpt = repository.findByName(name.toUpperCase());
        if (roleOpt.isPresent()) {
            List<UserProfile> users = new ArrayList<>();
            request.getUsernames().forEach(username -> {
                UserProfile userProfile = userProfileService.findByUsername(username);
                userProfile.getRoles().add(roleOpt.get());
                users.add(userProfile);
            });
            userProfileService.saveAll(users);
        } else {
            throw new BusinessException("This Role is existed, Name: " + name.toUpperCase());
        }
    }

    public List<Role> getRoles() {
        return repository.findAll();
    }
}
