package com.nefu.myspringboot.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nefu.myspringboot.common.Hour;
import com.nefu.myspringboot.entity.Laboratory;
import com.nefu.myspringboot.entity.ScheduleCourse;
import com.nefu.myspringboot.entity.Teacher;
import com.nefu.myspringboot.entity.User;
import com.nefu.myspringboot.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class InitService implements InitializingBean {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private LaboratoryMapper laboratoryMapper;
    @Autowired
    private ScheduleCourseMapper scheduleCourseMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        // id字段必然存在，数量为0表示用户名表为空。
        // 初始化管理员用户，赋权限值
        int count = userMapper.selectCount(new QueryWrapper<User>().select("id"));
        if (count == 0) {
            User user = User.builder()
                    .name("admin")
                    .number("admin")
                    .password(encoder.encode("admin"))
                    .role(9)
                    .build();
            userMapper.insert(user);
            User u = User.builder()
                    .name("BO")
                    .number("1002")
                    .password(encoder.encode("1002"))
                    .role(5)
                    .build();
            userMapper.insert(u);
            Teacher t = Teacher.builder()
                    .id(u.getId())
                    .title("讲师")
                    .build();
            teacherMapper.insert(t);
            Laboratory l = Laboratory.builder()
                    .number(901L)
                    .computerNumber(50)
                    .build();
            Laboratory laboratory = Laboratory.builder()
                    .number(902L)
                    .computerNumber(50)
                    .build();
            laboratoryMapper.insert(l);
            laboratoryMapper.insert(laboratory);
            for (int i = 1; i <= Hour.WEEK; i++) {
                for (int j = 1; j <= Hour.DAY; j++) {
                    for (int k = 1; k <= Hour.SECTION; k++) {
                        ScheduleCourse s = ScheduleCourse.builder()
                                .labId(laboratory.getNumber())
                                .week(i)
                                .name("无")
                                .state(true)
                                .day(j)
                                .section(k)
                                .build();
                        scheduleCourseMapper.insert(s);
                        ScheduleCourse s2 = ScheduleCourse.builder()
                                .labId(l.getNumber())
                                .week(i)
                                .name("无")
                                .state(true)
                                .day(j)
                                .section(k)
                                .build();
                        scheduleCourseMapper.insert(s2);
                    }
                }
            }
        }
    }
}
