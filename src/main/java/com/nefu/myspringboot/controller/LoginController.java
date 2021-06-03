package com.nefu.myspringboot.controller;

import com.nefu.myspringboot.entity.Student;
import com.nefu.myspringboot.entity.Teacher;
import com.nefu.myspringboot.entity.User;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Api(value = "处理登录/未登录操作请求")
@RestController
@RequestMapping("/api/")
public class LoginController {
    @PostMapping("/login")
    public String login() {
        return "sss";
    }
   @GetMapping("/get/user")
    public User user(){
        return user();
   }
    @GetMapping("/get/teacher")
   public Teacher teacher(){
        return teacher();
   }
   @GetMapping("/get/student")
    public Student student(){
        return student();
   }
}
