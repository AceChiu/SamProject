package com.ace.api;

import com.ace.common.ResourcePaths;
import com.ace.entity.Role;
import com.ace.request.RoleDtoRequest;
import com.ace.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ACE.CHIU
 * @create: 2025-02-04
 */

@RestController
@RequestMapping("/api/" + ResourcePaths.ROLE)
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping(value = "/create")
    public Role create(@RequestBody RoleDtoRequest request) {
        return roleService.create(request);
    }

    @PostMapping(value = "/update")
    public Role update(@RequestBody RoleDtoRequest request) {
        return roleService.update(request);
    }
}
