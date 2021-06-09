package com.nefu.myspringboot.controller;


import com.nefu.myspringboot.dto.TeacherDTO;
import com.nefu.myspringboot.entity.Teacher;
import com.nefu.myspringboot.entity.User;
import com.nefu.myspringboot.service.UserService;
import com.nefu.myspringboot.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(value = "处理超管操作请求", tags = {"Authorization, Admin"})
@RestController
@RequestMapping("/api/admin/")
public class AdminController {
    @Autowired
    private UserService userService;

    @ApiOperation("添加教师")
    @PostMapping("addteachers")
    public ResultVO addTeacher(@RequestBody TeacherDTO teacher) {
        Teacher t = userService.addTeacher(teacher);
        return ResultVO.success(Map.of("teacher", t));
    }
    @GetMapping("getteachers")
    public ResultVO getTeacher(){
        List<TeacherDTO> t=userService.teacherList();
        return ResultVO.success(Map.of("teachers",t));
    }
    @PostMapping("getusers")
    public ResultVO getTeacherByname(@RequestBody String name){
        List<User> users=  userService.getUserByName(name);
        return ResultVO.success(Map.of("user",users));
    }
}
