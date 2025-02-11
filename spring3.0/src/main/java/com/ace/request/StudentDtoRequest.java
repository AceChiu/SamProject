package com.ace.request;

import com.ace.entity.Region;
import lombok.Data;

/**
 * @author: ACE.CHIU
 * @create: 2025-02-11
 */
@Data
public class StudentDtoRequest {
    private String username;

    private String school;
    private String guardianName;
    private String guardianPhone;
    private String guardianEmail;
    private long regionId;
}
