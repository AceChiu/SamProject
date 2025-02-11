package com.ace.service;

import com.ace.entity.Student;
import com.ace.entity.Teacher;
import com.ace.entity.UserProfile;
import com.ace.exception.BusinessException;
import com.ace.repository.BasicJpaRepository;
import com.ace.repository.RegionRepository;
import com.ace.repository.StudentRepository;
import com.ace.request.StudentDtoRequest;
import com.ace.request.TeacherDtoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author: ACE.CHIU
 * @create: 2021-03-19
 */
@Service
public class StudentService extends BasicService<Student> {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private RegionRepository regionRepository;

    @Override
    public BasicJpaRepository<Student> getRepository() {
        return repository;
    }

    @Autowired
    private UserProfileService userProfileService;

    public Student create(StudentDtoRequest request) {
        UserProfile userProfile = userProfileService.findByUsername(request.getUsername());
        if (userProfile == null) {
            throw new BusinessException("This user is not existed, username: " + request.getUsername());
        }

        Optional<Student> studentOpt = repository.findByUserProfile(userProfile);
        if (studentOpt.isPresent()) {
            throw new BusinessException("This student is existed, Email: " + request.getUsername());
        }
        Student student = new Student();
        student.setUserProfile(userProfile);
        student.setGuardianEmail(request.getGuardianEmail());
        student.setGuardianName(request.getGuardianName());
        student.setSchool(request.getSchool());
        student.setRegion(regionRepository.findById(request.getRegionId()).orElse(null));
        return repository.save(student);
    }
}
