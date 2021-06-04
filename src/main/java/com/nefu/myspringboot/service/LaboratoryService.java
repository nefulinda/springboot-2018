package com.nefu.myspringboot.service;

import com.nefu.myspringboot.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class LaboratoryService {
    //    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentCourseMapper studentCourseMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private LaboratoryMapper laboratoryMapper;
    @Autowired
    private LaboratoryStudentMapper laboratoryStudentMapper;
}
