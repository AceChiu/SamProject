package com.ace.request;

import lombok.Data;
import java.util.List;

/**
 * @author: ACE.CHIU
 * @create: 2025-01-30
 */
@Data
public class RoleDtoRequest {
    private List<String> usernames;
}
