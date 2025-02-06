package com.ace.request;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author: ACE.CHIU
 * @create: 2025-01-30
 */
@Getter
@Setter
public class RoleDtoRequest {
    private String name;
    private List<String> usernames;
}
