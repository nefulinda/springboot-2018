package com.nefu.myspringboot.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nefu.myspringboot.common.Role;
import com.nefu.myspringboot.dto.TeacherDTO;
import com.nefu.myspringboot.entity.Student;
import com.nefu.myspringboot.entity.Teacher;
import com.nefu.myspringboot.entity.User;
import com.nefu.myspringboot.mapper.StudentMapper;
import com.nefu.myspringboot.mapper.TeacherMapper;
import com.nefu.myspringboot.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class UserService {

    private PasswordEncoder encoder;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private StudentMapper studentMapper;


    public List<User> getUserByName(String name) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name).orderByDesc("number");
        return userMapper.selectList(queryWrapper);
    }

    public User getUserByNumber(String number) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("number", number);
        return userMapper.selectOne(queryWrapper);
    }

    public void updatePassword(long uid, String pwd) {
        userMapper.updateById(User.builder().id(uid).password(encoder.encode(pwd)).build());
    }

    public Teacher addTeacher(TeacherDTO teacher) {
        User u = User.builder()
                .name(teacher.getName())
                .number(teacher.getNumber())
                .password(encoder.encode(teacher.getNumber()))
                .role(Role.TEACHER)
                .build();
        userMapper.insert(u);

        // user保存后，会获取到id值。作为teacher表的id。共用主键
        Teacher t = Teacher.builder()
                .id(u.getId())
                .college(teacher.getCollege())
                .post(teacher.getPost())
                .build();
        teacherMapper.insert(t);
        return t;
    }
    public void addStudents(){

    }
}
