package com.ace.service;

import com.ace.dto.UserProfileDto;
import com.ace.entity.Role;
import com.ace.entity.UserProfile;
import com.ace.exception.BusinessException;
import com.ace.repository.BasicJpaRepository;
import com.ace.repository.RoleRepository;
import com.ace.repository.UserProfileRepository;
import com.ace.request.UserProfileDtoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author: ACE.CHIU
 * @create: 2021-02-22
 */
//@Slf4j
@Service
public class UserProfileService extends BasicService<UserProfile> {

  private static final String USER = "USER";
  private static final String ADMIN = "ADMIN";
  @Autowired
  private UserProfileRepository repository;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private RoleRepository roleRepository;

  public UserProfile create(UserProfileDto dto) {
    UserProfile userProfile = new UserProfile();
    userProfile.setUsername(dto.getUsername());
    userProfile.setPassword(passwordEncoder.encode(dto.getPassword()));
    userProfile.setEmail(dto.getEmail());
    userProfile.setGoogleId(dto.getGoogleId());
    userProfile.setFamilyName(dto.getFamilyName());
    userProfile.setGivenName(dto.getGivenName());
    userProfile.setName(dto.getName());
    Optional<Role> roleOpt = roleRepository.findByName(USER);
    if (!roleOpt.isPresent()) {
        Role role = new Role();
        role.setName(USER);
        role = roleRepository.save(role);
        userProfile.getRoles().add(role);
        role = new Role();
        role.setName(ADMIN);
        role = roleRepository.save(role);
        userProfile.getRoles().add(role);
    } else {
      userProfile.getRoles().add(roleOpt.get());
    }
    return repository.save(userProfile);
  }
  
  public UserProfile update(UserProfileDtoRequest request) {
    Optional<UserProfile> userProfileOpt = repository.findByUsername(request.getUsername());
    if (userProfileOpt.isPresent()) {
      UserProfile userProfile = userProfileOpt.get();
      userProfile.setBirthday(request.getBirthday());
      userProfile.setAddress(request.getAddress());
      userProfile.setPhone(request.getPhone());
      return repository.save(userProfile);
    } else {
      throw new BusinessException("This user is not existed, username: " + request.getUsername());
    }
  }

  public UserProfile findById(long id) {
    return repository.findById(id).orElse(null);
  }

  public UserProfile findByUsername(String username) {
    return repository.findByUsername(username).orElse(null);
  }
  @Override
  public BasicJpaRepository<UserProfile> getRepository() {
    return repository;
  }
}
