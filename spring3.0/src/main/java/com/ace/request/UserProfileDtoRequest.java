package com.ace.request;

import lombok.Builder;
import lombok.Data;

/**
 * @author: ACE.CHIU
 * @create: 2025-02-10
 */
@Data
@Builder
public class UserProfileDtoRequest {
    private String username;
    private String name;
    private String familyName;
    private String givenName;
    private String birthday;
    private String phone;
    private String cellPhone;
    private String address;
}
