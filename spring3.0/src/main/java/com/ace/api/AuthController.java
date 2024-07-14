package com.ace.api;

import com.ace.common.ResourcePaths;
import com.ace.config.SecurityConfig;
import com.ace.dto.LoginDto;
import com.ace.dto.UserProfileDto;
import com.ace.entity.UserProfile;
import com.ace.repository.RoleRepository;
import com.ace.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/" + ResourcePaths.AUTH)
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private RoleRepository roleRepository;

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getGoogleId()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("Success Login", HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public UserProfileDto create(@RequestBody UserProfileDto userProfileDto) {
        UserProfile userProfile = userProfileService.create(userProfileDto);
        return UserProfileDto.builder()
                .email(userProfile.getEmail())
                .name(userProfile.getName())
                .googleId(userProfile.getGoogleId())
                .familyName(userProfile.getFamilyName())
                .givenName(userProfile.getGivenName())
                .build();
    }
}
