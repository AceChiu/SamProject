package com.delta.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delta.dto.UserProfileDto;
import com.delta.entity.UserProfile;
import com.delta.repository.BasicJpaRepository;
import com.delta.repository.UserProfileRepository;

/**
 * @author: ACE.CHIU
 * @create: 2021-02-22
 */
//@Slf4j
@Service
public class UserProfileService extends BasicService<UserProfile> {

  @Autowired
  private UserProfileRepository repository;

  public UserProfile create(UserProfileDto dto) {
    UserProfile userProfile = new UserProfile();
    userProfile.setFamilyName(dto.getFamilyName());
    userProfile.setGivenName(dto.getGivenName());
    userProfile.setName(dto.getName());
    userProfile.setEmail(dto.getEmail());
    return repository.save(userProfile);
  }
  
  public UserProfile update(UserProfileDto userProfileDto) {
    UserProfile userProfile = repository.findByEmail(userProfileDto.getEmail())
        .orElse(new UserProfile());
    userProfile.setBirthday(userProfileDto.getBirthday());;
    return repository.save(userProfile);

  }

  public Optional<UserProfile> findByEmail(String email) {
    return repository.findByEmail(email);
  }
  @Override
  public BasicJpaRepository<UserProfile> getRepository() {
    return repository;
  }
}
