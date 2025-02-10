package com.ace.api;

import com.ace.common.ResourcePaths;
import com.ace.dto.RoleDto;
import com.ace.dto.UserProfileDto;
import com.ace.entity.Role;
import com.ace.entity.UserProfile;
import com.ace.request.UserProfileDtoRequest;
import com.ace.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author: ACE.CHIU
 * @create: 2022-08-16
 */
//@Slf4j
@RestController
@RequestMapping("/api/" + ResourcePaths.USER_PROFILE)
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping(value = "/current-user")
    public UserProfileDto currentUserName(Authentication authentication) {
        UserProfile userProfile = userProfileService.findByUsername(authentication.getName());
        UserProfileDto dto = UserProfileDto.builder()
                .id(userProfile.getId())
                .username(userProfile.getUsername())
                .email(userProfile.getEmail())
                .name(userProfile.getName())
                .familyName(userProfile.getFamilyName())
                .givenName(userProfile.getGivenName())
                .address(userProfile.getAddress())
                .birthday(userProfile.getBirthday())
                .phone(userProfile.getPhone())
                .roleNames(userProfile.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                .build();
        return dto;
    }

    @GetMapping(value = "/find/id/{id}")
    public UserProfileDto findByUsername(@PathVariable long id) {
        UserProfile userProfile = userProfileService.findById(id);
        if (Objects.nonNull(userProfile)) {
            return UserProfileDto.builder()
                    .id(userProfile.getId())
                    .username(userProfile.getUsername())
                    .email(userProfile.getEmail())
                    .name(userProfile.getName()).build();
        } else {
            return null;
        }
    }

    @GetMapping(value = "/find/username/{username}")
    public UserProfileDto findByUsername(@PathVariable String username) {
        UserProfile userProfile = userProfileService.findByUsername(username);
        if (Objects.nonNull(userProfile)) {
            return UserProfileDto.builder()
                    .id(userProfile.getId())
                    .username(userProfile.getUsername())
                    .email(userProfile.getEmail())
                    .name(userProfile.getName()).build();
        } else {
            return null;
        }
    }

    @PostMapping(value = "/update")
    public UserProfileDto update(@RequestBody UserProfileDtoRequest userProfileDto) {
        UserProfile userProfile = userProfileService.update(userProfileDto);
        return UserProfileDto.builder()
                .id(userProfile.getId())
                .username(userProfile.getUsername())
                .email(userProfile.getEmail())
                .name(userProfile.getName())
                .familyName(userProfile.getFamilyName())
                .givenName(userProfile.getGivenName())
                .address(userProfile.getAddress())
                .birthday(userProfile.getBirthday())
                .phone(userProfile.getPhone())
                .cellPhone(userProfile.getCellPhone())
                .build();
    }
}
