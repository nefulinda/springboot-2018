package com.nefu.myspringboot.controller;


import com.nefu.myspringboot.common.EncryptComponent;
import com.nefu.myspringboot.entity.Student;
import com.nefu.myspringboot.entity.Teacher;
import com.nefu.myspringboot.entity.User;
import com.nefu.myspringboot.mapper.StudentMapper;
import com.nefu.myspringboot.mapper.TeacherMapper;
import com.nefu.myspringboot.service.UserService;
import com.nefu.myspringboot.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Api(value = "处理登录/未登录操作请求")
@RestController
@RequestMapping("/api/")
public class LoginController {
    @Autowired
    private EncryptComponent encryptComponent;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserService userService;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private StudentMapper studentMapper;

    @GetMapping("welcome")
    public ResultVO welcome() {
        return ResultVO.success(Map.of("messages", "此请求无需登录", "students", userService.listStudents()));
    }

    @ApiOperation(value = "登录")
    @PostMapping("login")
    public ResultVO login(@RequestBody User user, HttpServletResponse response) {
        User u = userService.getUserByNumber(user.getNumber());
        if (u == null || !encoder.matches(user.getPassword(), u.getPassword())) {
            return ResultVO.error(401, "用户名密码错误");
        }
        Teacher t = teacherMapper.selectById(u.getId());
        String token = encryptComponent.encrypt(Map.of("uid", u.getId(),"role", u.getRole()));
        response.addHeader("token", token);
        if (t != null) {
            return ResultVO.success(Map.of("tid",u.getNumber(),"role", u.getRole().toString()));
        }
        return ResultVO.success(Map.of("role", u.getRole().toString()));
    }

}
