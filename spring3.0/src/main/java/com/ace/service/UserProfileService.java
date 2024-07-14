package com.ace.service;

import com.ace.dto.UserProfileDto;
import com.ace.entity.UserProfile;
import com.ace.repository.BasicJpaRepository;
import com.ace.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author: ACE.CHIU
 * @create: 2021-02-22
 */
//@Slf4j
@Service
public class UserProfileService extends BasicService<UserProfile> {

  @Autowired
  private UserProfileRepository repository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  public UserProfile create(UserProfileDto dto) {
    UserProfile userProfile = new UserProfile();
    userProfile.setGoogleId(dto.getGoogleId());
    userProfile.setEncodeGoogleId(passwordEncoder.encode(dto.getGoogleId()));
    userProfile.setFamilyName(dto.getFamilyName());
    userProfile.setGivenName(dto.getGivenName());
    userProfile.setName(dto.getName());
    userProfile.setEmail(dto.getEmail());
    return repository.save(userProfile);
  }
  
  public UserProfile update(UserProfileDto userProfileDto) {
    UserProfile userProfile = repository.findByEmail(userProfileDto.getEmail())
        .orElse(new UserProfile());
    userProfile.setBirthday(userProfileDto.getBirthday());
    userProfile.setAddress(userProfileDto.getAddress());
    userProfile.setPhone(userProfileDto.getPhone());
    return repository.save(userProfile);

  }

  public UserProfile findByEmail(String email) {
    return repository.findByEmail(email).orElse(null);
  }
  @Override
  public BasicJpaRepository<UserProfile> getRepository() {
    return repository;
  }
}
