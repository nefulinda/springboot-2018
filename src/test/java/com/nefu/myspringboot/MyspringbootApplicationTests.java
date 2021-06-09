package com.nefu.myspringboot;

//import com.alibaba.druid.pool.DruidDataSource;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nefu.myspringboot.common.Hour;
import com.nefu.myspringboot.dto.CourseDTO;
import com.nefu.myspringboot.dto.LaboratoryDTO;
import com.nefu.myspringboot.dto.StudentDTO;
import com.nefu.myspringboot.dto.TeacherDTO;
import com.nefu.myspringboot.entity.Course;
import com.nefu.myspringboot.entity.LabCourse;
import com.nefu.myspringboot.entity.Teacher;
import com.nefu.myspringboot.entity.User;
import com.nefu.myspringboot.mapper.*;
import com.nefu.myspringboot.service.UserService;
import com.nefu.myspringboot.utils.LabUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
@Slf4j
@SpringBootTest
class MyspringbootApplicationTests {
    @Autowired
    private UserService userService;
    @Autowired
    private LabCourseMapper labCourseMapper;
    @Autowired
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


    @Test
    void findAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    void addUser() {
        User user = new User();
        user.setName("admin");
        user.setNumber("2018214315");
        user.setPassword("2018214315");
        user.setRole(9);
        userMapper.insert(user);
    }

    @Test
    public void getUserByName() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "admin").orderByAsc("number");
        for (User user : userMapper.selectList(queryWrapper)) {
            System.out.println(user);
        }
    }

    @Test
    void selectUser() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("number", "2018214316");
        System.out.println(userMapper.selectOne(queryWrapper));

    }

    @Test
    void updateUser() {
        User user = userMapper.selectById(1400028053758496769L);
        user.setName("Admin");
        user.setRole(9);
        userMapper.updateById(user);

    }

    //分页查询
    @Test
    void selectPage() {
        Page<User> page = new Page(2, 2);
        Page<User> userPage = userMapper.selectPage(page, null);
        //返回对象得到分页所有数据
        long pages = userPage.getPages();//总页数
        long current = userPage.getCurrent();//当前页
        List<User> records = userPage.getRecords();//查询数据集合
        long total = userPage.getTotal();//总记录数
        boolean hasNext = userPage.hasNext();//下一页
        boolean hasPrevious = userPage.hasPrevious();//上一页
        System.out.println(pages);
        System.out.println(current);
        for (User user : records) {
            System.out.println(user);
        }
        System.out.println(total);
        System.out.println(hasNext);
        System.out.println(hasPrevious);

    }
    //DI注入数据源
//    @Autowired
//    DataSource dataSource;
//
//    @Test
//    public void contextLoads() throws SQLException {
//        //看一下默认数据源
//        System.out.println(dataSource.getClass());
//        //获得连接
//        Connection connection = dataSource.getConnection();
//        System.out.println(connection);
//
//        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
//        System.out.println("druidDataSource 数据源最大连接数：" + druidDataSource.getMaxActive());
//        System.out.println("druidDataSource 数据源初始化连接数：" + druidDataSource.getInitialSize());
//
//        //关闭连接
//        connection.close();
//    }

    @Test
    void addLab() {


        Teacher t1 = Teacher.builder().id(4548469L).post("讲师").college("信息").build();
        CourseDTO c1 = CourseDTO.builder().cid(56446L).name("C语言").teacher(t1).week(7).day(5).classHour(4).build();
        CourseDTO c2 = CourseDTO.builder().cid(56499L).name("java语言").teacher(t1).week(6).day(5).classHour(4).build();
        LaboratoryDTO lab = LaboratoryDTO.builder()
                .labId(922L).lid(45645555555555L).labNumber(60).courses(List.of(c1, c2)).build();

        for (int m = 0; m < lab.getCourses().size(); m++) {
            //如果该段时间内的实验室被预约了 则终止
            for (int i = 1; i <= Hour.WEEK; i++) {
                for (int j = 1; j <= Hour.DAY; j++) {
                    for (int k = 1; k <= Hour.SECTION; k++) {
                        if (lab.getCourses().get(m).getWeek().intValue() == i
                                && lab.getCourses().get(m).getDay().intValue() == j
                                && lab.getCourses().get(m).getClassHour().intValue() == j
                                && LabUtils.labList[i][j][k] == 1) {
                        }
                    }
                }
            }
            for (int i = 1; i <= Hour.WEEK; i++) {
                for (int j = 1; j <= Hour.DAY; j++) {
                    for (int k = 1; k <= Hour.SECTION; k++) {
                        if (lab.getCourses().get(m).getWeek().intValue() == i
                                && lab.getCourses().get(m).getDay().intValue() == j
                                && lab.getCourses().get(m).getClassHour().intValue() == j
                                && LabUtils.labList[i][j][k] == 0) {
                            LabUtils.labList[i][j][k] = 1;
                            lab.getCourses().get(m).setState(true);

                        }
                    }
                }
            }
            LabCourse labCourse = LabCourse.builder()
                    .id(lab.getLid())
                    .labId(lab.getLabId())
                    .cid(lab.getCourses().get(m).getCid())
                    .build();
            labCourseMapper.insert(labCourse);
            Course c = Course.builder()
                    .id(lab.getCourses().get(m).getCid())
                    .name(lab.getCourses().get(m).getName())
                    .teacherId(lab.getCourses().get(m).getTeacher().getId())
                    .build();
            courseMapper.insert(c);
        }

    }

    @Test
    void deletelab() {
        Teacher t1 = Teacher.builder().id(4548469L).post("讲师").college("信息").build();
        CourseDTO c1 = CourseDTO.builder().cid(56446L).name("C语言").teacher(t1).week(7).day(5).classHour(4).build();
        CourseDTO c2 = CourseDTO.builder().cid(56499L).name("java语言").teacher(t1).week(6).day(5).classHour(4).build();
        LaboratoryDTO lab = LaboratoryDTO.builder()
                .labId(922L).lid(45645555555555L).labNumber(60).courses(List.of(c1, c2)).build();

        for (int m = 0; m < lab.getCourses().size(); m++) {

             labCourseMapper.deleteById(lab.getLid());
            courseMapper.deleteById(lab.getCourses().get(m).getCid());

            for (int i = 1; i <= Hour.WEEK; i++) {
                for (int j = 1; j <= Hour.DAY; j++) {
                    for (int k = 1; k <= Hour.SECTION; k++) {
                        if (lab.getCourses().get(m).getWeek().intValue() == i
                                && lab.getCourses().get(m).getDay().intValue() == j
                                && lab.getCourses().get(m).getClassHour().intValue() == j
                        ) {
                            LabUtils.labList[i][j][k] = 0;
                            lab.getCourses().get(m).setState(false);
                        }
                    }
                }
            }
        }
    }
    @Test
    public void test2() {
        TeacherDTO teacherDTO = TeacherDTO.builder()
                .name("BO")
                .number("1002")
                .post("讲师")
                .college("信息院")
                .build();
        userService.addTeacher(teacherDTO);
    }
    @Test
    public void test3() {
        long cid = 1384896304762638337L;
        StudentDTO s1 = StudentDTO.builder()
                .name("Jack")
                .clazz("软件1班")
                .number("204600010001")
                .build();
        StudentDTO s2 = StudentDTO.builder()
                .name("Tom")
                .clazz("软件1班")
                .number("204600010002")
                .build();
        StudentDTO s3 = StudentDTO.builder()
                .name("Peter")
                .clazz("软件1班")
                .number("204600010003")
                .build();
        StudentDTO s4 = StudentDTO.builder()
                .name("Mean")
                .clazz("软件1班")
                .number("204600010004")
                .build();
        userService.addStudents(List.of(s1, s2, s3, s4), cid);
    }
    @Test
    public void test4() {


        List<StudentDTO> studentDTOS = userService.listStudents();
        System.out.println(studentDTOS.size());
        System.out.println(studentDTOS.getClass().getName());
        for (StudentDTO s : studentDTOS) {
            System.out.println(s.getName());
        }

    }
}


