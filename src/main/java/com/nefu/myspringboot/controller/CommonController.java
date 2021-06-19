package com.nefu.myspringboot.controller;


import com.nefu.myspringboot.common.Role;
import com.nefu.myspringboot.dto.LaboratoryDTO;
import com.nefu.myspringboot.entity.Course;
import com.nefu.myspringboot.entity.Laboratory;
import com.nefu.myspringboot.service.CourseService;
import com.nefu.myspringboot.service.LaboratoryService;
import com.nefu.myspringboot.service.UserService;
import com.nefu.myspringboot.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(value = "处理用户通用操作请求", tags = "Authorization")
@Slf4j
@RestController
@RequestMapping("/api/common/")
public class CommonController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private LaboratoryService laboratoryService;

//    @ApiOperation("登录后首页信息，加载用户的全部课程")
//    @GetMapping("home")
//    public ResultVO getCourses(@RequestAttribute("uid") long uid, @RequestAttribute("role") int role) {
//        List<Course> courses = role == Role.TEACHER
//                ? courseService.listCoursesByTid(uid)
//                : courseService.listCoursesBySid(uid);
//        return ResultVO.success(Map.of("courses", courses));
//    }
    @ApiOperation("修改密码")
    @PatchMapping("password")
    public ResultVO patchPassword(@RequestBody Map<String, String> map, @RequestAttribute("uid") long uid) {
        userService.updatePassword(uid, map.get("password"));
        return ResultVO.success(Map.of());
    }

    @ApiOperation("获取全部实验室列表")
    @GetMapping("labs")
    public ResultVO getLab ()  {
       List<Laboratory> labs  = laboratoryService.getListLab();
       return ResultVO.success(Map.of("labs",labs));
    }
    @ApiOperation("获取实验室课程列表")
    @GetMapping("orderlabscourses")
    public ResultVO getLabsCourses ()  {
       return ResultVO.success(Map.of("labcourses",laboratoryService.getAllSchedule()));
    }

}
