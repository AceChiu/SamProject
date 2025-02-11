package com.ace.api;

import com.ace.common.ResourcePaths;
import com.ace.service.StudentService;
import com.ace.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ACE.CHIU
 * @create: 2025-02-10
 */

@RestController
@RequestMapping("/api/" + ResourcePaths.STUDENT)
public class StudentController {

    @Autowired
    private StudentService studentService;
    
}
