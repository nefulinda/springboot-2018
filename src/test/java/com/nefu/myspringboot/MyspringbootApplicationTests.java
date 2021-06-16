package com.nefu.myspringboot;

//import com.alibaba.druid.pool.DruidDataSource;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nefu.myspringboot.common.Hour;
import com.nefu.myspringboot.dto.CourseDTO;
import com.nefu.myspringboot.dto.LaboratoryDTO;
import com.nefu.myspringboot.dto.StudentDTO;
import com.nefu.myspringboot.dto.TeacherDTO;
import com.nefu.myspringboot.entity.*;
import com.nefu.myspringboot.mapper.*;
import com.nefu.myspringboot.service.UserService;
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
    public void test2() {
        TeacherDTO teacherDTO = TeacherDTO.builder()
                .name("BO")
                .number("1006")
                .post("讲师")
                .college("信息院")
                .build();
        Teacher t = userService.addTeacher(teacherDTO);
        System.out.println(t);
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

    @Test
    public void addssLab() {
        Laboratory l = Laboratory.builder()
                .number(922)
                .computerNumber(60)
                .build();
        laboratoryMapper.insert(l);

    }
}


