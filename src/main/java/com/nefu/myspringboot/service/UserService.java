package com.nefu.myspringboot.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nefu.myspringboot.common.Role;
import com.nefu.myspringboot.dto.LaboratoryDTO;
import com.nefu.myspringboot.dto.StudentDTO;
import com.nefu.myspringboot.dto.TeacherDTO;
import com.nefu.myspringboot.entity.Student;
import com.nefu.myspringboot.entity.StudentCourse;
import com.nefu.myspringboot.entity.Teacher;
import com.nefu.myspringboot.entity.User;
import com.nefu.myspringboot.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class UserService {
    //@Autowired
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

    public void deleteTeacher(TeacherDTO teacher) {
        if (teacher != null) {
            teacherMapper.deleteById(teacher.getTid());
            userMapper.deleteById(teacher.getTid());
        }
    }

    /*
     *  查询老师
     * 具体未实现
     *
     */
    @Cacheable(value = "teachers")
    public List<TeacherDTO> teacherList() {
        return teacherMapper.list();
    }

    public boolean updateTeacher(TeacherDTO teacher) {
        Teacher t = teacherMapper.selectById(teacher.getTid());
        User u = userMapper.selectById(teacher.getTid());
        if (t != null && u != null) {
            t.setCollege(teacher.getCollege());
            t.setPost(teacher.getPost());
            u.setName(teacher.getName());
            u.setNumber(teacher.getNumber());
            return true;
        } else {
            return false;
        }
    }

    @CacheEvict(value = "students", allEntries = true)
    public void addStudents(List<StudentDTO> students, long cid) {
        for (StudentDTO sDTO : students) {
            User u = userMapper.getByNumber(sDTO.getNumber());
            // 学生已经存在
            if (u != null) {
                StudentCourse sc = studentCourseMapper.getStudentCourse(u.getId(), cid);
                // 学生与课程已经建立关系
                if (sc != null) {
                    continue;
                }
                // 学生存在，但未与课程建立关系
                sc = StudentCourse.builder()
                        .studentId(u.getId())
                        .courseId(cid)
                        .build();
                studentCourseMapper.insert(sc);
                continue;
            }
            // 学生不存在
            u = User.builder()
                    .name(sDTO.getName())
                    .role(Role.STUDENT)
                    .number(sDTO.getNumber())
                    .password(encoder.encode(sDTO.getNumber()))
                    .build();
            userMapper.insert(u);

            Student st = Student.builder()
                    .clazz(sDTO.getClazz())
                    .id(u.getId())
                    .build();
            studentMapper.insert(st);
            // 与课程建立关系
            StudentCourse sc = StudentCourse.builder()
                    .studentId(u.getId())
                    .courseId(cid)
                    .build();
            studentCourseMapper.insert(sc);
        }
    }

    public boolean updateStudent(StudentDTO student) {
        Student s = studentMapper.selectById(student.getSid());
        User u = userMapper.selectById(student.getSid());
        if (s != null && u != null) {
            s.setClazz(student.getClazz());
            s.setCollege(student.getCollege());
            u.setName(student.getName());
            u.setNumber(student.getNumber());
            return true;
        } else
            return false;

    }

    public void deleteStudent(StudentDTO student) {
        if (student != null) {
            teacherMapper.deleteById(student.getSid());
            userMapper.deleteById(student.getSid());
        }
    }

    @Cacheable(value = "students")
    public List<StudentDTO> listStudents() {
        return studentMapper.list();
    }

}
