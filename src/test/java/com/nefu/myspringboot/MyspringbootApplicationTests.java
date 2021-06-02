package com.nefu.myspringboot;

//import com.alibaba.druid.pool.DruidDataSource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nefu.myspringboot.entity.User;
import com.nefu.myspringboot.mapper.UserMapper;


import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MyspringbootApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Test
    void findAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }
    @Test
    void  addUser(){
        User user = new User();
        user.setName("Admin");
        user.setNumber("2018214316");
        user.setPassword("2018214316");
        user.setRole(9);
        userMapper.insert(user);
    }
    @Test
    void  selectUser(){
        User  user = userMapper.selectById(1400028053758496769L);
        System.out.println(user);

    }
    @Test
    void  updateUser(){
        User  user = userMapper.selectById(1400028053758496769L);
        user.setName("Admin");
        user.setRole(9);
        userMapper.updateById(user);

    }
    //分页查询
    @Test
    void  selectPage(){
        Page<User> page = new Page(1,3);
        Page<User> userPage = userMapper.selectPage(page,null);
        //返回对象得到分页所有数据
        long pages = userPage.getPages();//总页数
        long current = userPage.getCurrent();//当前页
        List<User> records = userPage.getRecords();//查询数据集合
       long total = userPage.getTotal();//总记录数
        boolean hasNext = userPage.hasNext();//下一页
        boolean hasPrevious = userPage.hasPrevious();//上一页

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

}
