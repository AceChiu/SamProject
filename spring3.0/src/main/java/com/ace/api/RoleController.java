package com.ace.api;

import com.ace.common.ResourcePaths;
import com.ace.entity.Role;
import com.ace.request.RoleDtoRequest;
import com.ace.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: ACE.CHIU
 * @create: 2025-02-04
 */

@RestController
@RequestMapping("/api/" + ResourcePaths.ROLE)
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping(value = "/create/{name}")
    public Role create(@PathVariable String name) {
        return roleService.create(name);
    }

    @PostMapping(value = "/update/{name}")
    public void update(@PathVariable String name, @RequestBody RoleDtoRequest request) {
        roleService.update(name, request);
    }
}
