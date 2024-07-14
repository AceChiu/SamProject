package com.ace.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: ACE.CHIU
 * @create: 2022-06-30
 */
@Data
@Builder
public class UserProfileDto {

    private String googleId;

    private String email;

    private String familyName;

    private String givenName;

    private String name;

    private String birthday;

    private String phone;

    private String address;
}
