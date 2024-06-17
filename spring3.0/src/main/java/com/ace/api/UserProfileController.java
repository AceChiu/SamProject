package com.ace.api;

import com.ace.common.ResourcePaths;
import com.ace.dto.UserProfileDto;
import com.ace.entity.UserProfile;
import com.ace.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: ACE.CHIU
 * @create: 2022-08-16
 */
//@Slf4j
@RestController
public class UserProfileController {

  @Autowired
  private UserProfileService userProfileService;

  @PostMapping(value = ResourcePaths.USER_PROFILE + "/create")
  public UserProfile create(@RequestBody UserProfileDto userProfileDto) {
    return userProfileService.create(userProfileDto);
  }

  @GetMapping(value = ResourcePaths.USER_PROFILE + "/find/email/{email}")
  public UserProfile findByEmail(@PathVariable String email) {
    return userProfileService.findByEmail(email).orElse(null);
  }

  @PostMapping(value = ResourcePaths.USER_PROFILE + "/upseart")
  public UserProfile update(@RequestBody UserProfileDto userProfileDto) {
    return userProfileService.update(userProfileDto);
  }
}
