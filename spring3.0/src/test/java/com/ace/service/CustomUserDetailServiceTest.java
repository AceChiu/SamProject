package com.ace.service;

import com.ace.entity.Role;
import com.ace.entity.UserProfile;
import com.ace.repository.UserProfileRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailServiceTest {
    @Mock
    private UserProfileRepository userProfileRepository;

    @InjectMocks
    private CustomUserDetailService customUserDetailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadUserByUsername_UserExists() {
        // Arrange: 模擬用戶和角色數據
        Role role1 = new Role();
        role1.setName("ROLE_USER");

        Role role2 = new Role();
        role2.setName("ROLE_ADMIN");

        UserProfile userProfile = new UserProfile();
        userProfile.setUsername("testuser");
        userProfile.setPassword("encryptedPassword");
        userProfile.setRoles(Arrays.asList(role1, role2));

        when(userProfileRepository.findByUsername("testuser")).thenReturn(Optional.of(userProfile));

        // Act: 調用測試方法
        UserDetails userDetails = customUserDetailService.loadUserByUsername("testuser");

        // Assert: 驗證結果
        assertNotNull(userDetails);
        assertEquals("testuser", userDetails.getUsername());
        assertEquals("encryptedPassword", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_USER")));
        assertTrue(userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN")));

        verify(userProfileRepository, times(1)).findByUsername("testuser");
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        // Arrange: 模擬用戶不存在的情況
        when(userProfileRepository.findByUsername("nonexistentuser")).thenReturn(Optional.empty());

        // Act & Assert: 驗證會拋出 UsernameNotFoundException
        assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailService.loadUserByUsername("nonexistentuser");
        });

        verify(userProfileRepository, times(1)).findByUsername("nonexistentuser");
    }
}
