package com.nefu.myspringboot.controller;


import com.nefu.myspringboot.dto.CourseDTO;
import com.nefu.myspringboot.dto.LaboratoryDTO;
import com.nefu.myspringboot.dto.TeacherDTO;
import com.nefu.myspringboot.entity.Course;
import com.nefu.myspringboot.entity.User;
import com.nefu.myspringboot.mapper.UserMapper;
import com.nefu.myspringboot.service.CourseService;
import com.nefu.myspringboot.service.LaboratoryService;
import com.nefu.myspringboot.service.UserService;
import com.nefu.myspringboot.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Api(value = "处理教师角色请求", tags = {"Authorization, Teacher"})
@RestController
@RequestMapping("/api/teacher/")
public class TeacherController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private LaboratoryService laboratoryService;

    @ApiOperation("添加课程，并返回当前教师全部课程")
    @PostMapping("addcourses")
    public ResultVO postCourse(@RequestBody Course course) {
        courseService.addCourse(course);
        return ResultVO.success(Map.of("courses", courseService.getCoursesByNumber(course.getTeacherId())));
    }
    @ApiOperation("通过课程名删除课程，并返回当前教师全部课程")
    @PostMapping("deletecourses")
    public ResultVO deleteCourse(@RequestBody Course course) {
        courseService.deleteCourse(course.getName());
        return ResultVO.success(Map.of("courses", courseService.getCoursesByNumber(course.getTeacherId())));
    }
    @ApiOperation("修改课程")
    @PostMapping("updatecourse")
    public ResultVO updatecourse(@RequestBody Course course) {
        courseService.updateCourse(course);
        return ResultVO.success(Map.of("updatecourse",course));
    }
    @ApiOperation("获取当前教师全部课程")
    @GetMapping("courses")
    public ResultVO getCourses(@RequestAttribute("uid") long uid) {
         User u= userService.getId(uid);
        return ResultVO.success(Map.of("courses", courseService.getCoursesByNumber(u.getNumber())));
    }

    @ApiOperation(value = "为指定课程添加学生",
            notes = "学生不存在，创建学生用户关联课程；学生已存在，直接关联课程")
    @PostMapping("students")
    public ResultVO postStudents(@RequestBody CourseDTO courseDTO, @RequestAttribute("uid") long uid) {
        // 严谨。先判断执行添加学生的教师，是否是此课程授课教师
        if (courseService.getCourse(uid, courseDTO.getCid()) == null) {
            return ResultVO.error(403, "教师仅能操作自己创建的课程");
        }
        userService.addStudents(courseDTO.getStudents(), courseDTO.getCid());
        return ResultVO.success(Map.of());
    }
    @ApiOperation("预约实验室")
    @PostMapping("orderlab")
    public ResultVO orderLab(@RequestBody LaboratoryDTO l) {
        System.out.println(l);
        laboratoryService.addLabDTO(l);
        return ResultVO.success(Map.of("orderlabs",laboratoryService.getAllSchedule()));
    }
//    @ApiOperation("取消预约实验室")
//    @PostMapping("unorderlab")
//    public ResultVO unorderLab(@RequestBody LaboratoryDTO l) {
//        laboratoryService.deleteLabDTO(l);
//        return ResultVO.success(Map.of("unorderlab",laboratoryService.getAllSchedule()));
//    }

}
