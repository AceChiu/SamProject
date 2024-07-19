package com.ace.api;

import com.ace.common.ResourcePaths;
import com.ace.dto.UserProfileDto;
import com.ace.entity.UserProfile;
import com.ace.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

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

    @GetMapping(value = "/find/username/{username}")
    public UserProfileDto findByUsername(@PathVariable String username) {
        UserProfile userProfile = userProfileService.findByUsername(username);
        if (Objects.nonNull(userProfile)) {
            return UserProfileDto.builder()
                    .username(userProfile.getUsername())
                    .email(userProfile.getEmail())
                    .name(userProfile.getName())
                .build();
        } else {
            return null;
        }
    }

    @GetMapping(value = "/find/email/{email}")
    public UserProfileDto findByEmail(@PathVariable String email) {
        UserProfile userProfile = userProfileService.findByEmail(email);
        if (Objects.nonNull(userProfile)) {
            return UserProfileDto.builder()
                    .username(userProfile.getUsername())
                    .email(userProfile.getEmail())
                    .name(userProfile.getName())
                .build();
        } else {
            return null;
        }
    }

    @PostMapping(value = "/update")
    public UserProfileDto update(@RequestBody UserProfileDto userProfileDto) {
        UserProfile userProfile = userProfileService.update(userProfileDto);
        return UserProfileDto.builder()
                .username(userProfile.getUsername())
                .email(userProfile.getEmail())
                .name(userProfile.getName())
                .familyName(userProfile.getFamilyName())
                .givenName(userProfile.getGivenName())
                .address(userProfileDto.getAddress())
                .birthday(userProfile.getBirthday())
                .phone(userProfile.getPhone())
                .build();
    }
}
