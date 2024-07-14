package com.ace.service;

import com.ace.entity.Role;
import com.ace.entity.UserProfile;
import com.ace.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    public UserProfileRepository userProfileRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserProfile userProfile = userProfileRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Google Authentication Error"));
        return new User(userProfile.getEmail(), userProfile.getGoogleId(), mapRolesToAuthorities(userProfile.getRoles()));
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream().map(role ->  new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
