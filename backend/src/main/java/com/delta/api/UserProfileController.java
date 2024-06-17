package com.delta.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.delta.common.ResourcePaths;
import com.delta.dto.UserProfileDto;
import com.delta.entity.UserProfile;
import com.delta.service.UserProfileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author: ACE.CHIU
 * @create: 2022-08-16
 */
//@Slf4j
@Api(value = "User Profile", tags = "使用者管理")
@RestController
public class UserProfileController {

  @Autowired
  private UserProfileService userProfileService;
  
  @ApiOperation(value = "新增一個 User")
  @PostMapping(value = ResourcePaths.USER_PROFILE + "/create")
  public UserProfile create(@RequestBody UserProfileDto userProfileDto) {
    return userProfileService.create(userProfileDto);
  }
  
  @ApiOperation(value = "根據 email 搜尋使用者")
  @GetMapping(value = ResourcePaths.USER_PROFILE + "/find/email/{email}")
  public UserProfile findByEmail(@ApiParam(value = "email") @PathVariable String email) {
    return userProfileService.findByEmail(email).orElse(null);
  }
  
  @ApiOperation(value = "維護使用者")
  @PostMapping(value = ResourcePaths.USER_PROFILE + "/upseart")
  public UserProfile update(@RequestBody UserProfileDto userProfileDto) {
    return userProfileService.update(userProfileDto);
  }
}
