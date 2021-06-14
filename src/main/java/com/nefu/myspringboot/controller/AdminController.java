package com.nefu.myspringboot.controller;


import com.nefu.myspringboot.dto.LaboratoryDTO;
import com.nefu.myspringboot.dto.TeacherDTO;
import com.nefu.myspringboot.entity.Laboratory;
import com.nefu.myspringboot.entity.Teacher;
import com.nefu.myspringboot.entity.User;
import com.nefu.myspringboot.mapper.*;
import com.nefu.myspringboot.service.CourseService;
import com.nefu.myspringboot.service.LaboratoryService;
import com.nefu.myspringboot.service.NoticeService;
import com.nefu.myspringboot.service.UserService;
import com.nefu.myspringboot.utils.LabUtils;
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
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private LaboratoryService laboratoryService;
    @Autowired
    private CourseService courseServicer;

    @ApiOperation("添加教师")
    @PostMapping("addteachers")
    public ResultVO addTeacher(@RequestBody TeacherDTO teacher) {
        Teacher t = userService.addTeacher(teacher);
        return ResultVO.success(Map.of("teacher", t));
    }

    @ApiOperation("获取教师列表")
    @GetMapping("getteachers")
    public ResultVO getTeacher() {
        List<TeacherDTO> t = userService.teacherList();
        return ResultVO.success(Map.of("teachers", t));
    }
    @ApiOperation("删除教师")
    @PostMapping("deleteteacher")
    public ResultVO deleteTeacher(@RequestAttribute("number") String number) {
        userService.deleteTeacher(number);
        return ResultVO.success(Map.of("teachernumber", number));
    }
    @ApiOperation("修改教师信息")
    @PostMapping("modifyteacher")
    public ResultVO modifyTeacher(@RequestBody TeacherDTO teacher) {
        userService.updateTeacher(teacher);
        return ResultVO.success(Map.of("update",teacher));
    }
    @ApiOperation("通过名字获取教师")
    @PostMapping("getusersname")
    public ResultVO getTeacherByname(@RequestAttribute("name") String name) {
        List<User> users = userService.getUserByName(name);
        return ResultVO.success(Map.of("user", users));
    }

    @ApiOperation("通知公告")
    @GetMapping("notice")
    public ResultVO getNotice() {
        return ResultVO.success(Map.of("notice",noticeService.getAllNotice()));
    }

    @ApiOperation("增加实验室")
    @PostMapping("addlabs")
    public ResultVO addLab(@RequestBody Laboratory laboratory) {
        laboratoryService.addLab(laboratory);
        return ResultVO.success(Map.of("addlabs",laboratoryService.selectLab(laboratory)));
    }

    @ApiOperation("删除实验室")
    @PostMapping("deletelab")
    public ResultVO deleteLab(@RequestAttribute("name") String name) {
        laboratoryService.deleteLab(name);
        return ResultVO.success(Map.of("deletelab",name));
    }
    @ApiOperation("获取全部课程")
    @GetMapping("getallcourses")
    public ResultVO getCourses() {
        return ResultVO.success(Map.of("deletelabs",courseServicer.getAllCourses()));
    }
    @ApiOperation("通过课程号获取课程")
    @GetMapping("getidcourse")
    public ResultVO getCourse(@RequestAttribute("sid") Long sid) {
        return ResultVO.success(Map.of("deletelabs",courseServicer.listCoursesBySid(sid)));
    }
    @ApiOperation("获取指定老师的课程")
    @GetMapping("gettidcourse")
    public ResultVO getTeacherCourse(@RequestAttribute("tid") Long tid) {
        return ResultVO.success(Map.of("deletelabs",courseServicer.listCoursesByTid(tid)));
    }
    @ApiOperation("预约实验室")
    @PostMapping("orderlab")
    public ResultVO orderLab(@RequestBody LaboratoryDTO l) {
        laboratoryService.addLabDTO(l);
        return ResultVO.success(Map.of("orderlab",l,"status", LabUtils.labList));
    }
    @ApiOperation("取消预约实验室")
    @PostMapping("unorderlab")
    public ResultVO unorderLab(@RequestBody LaboratoryDTO l) {
        laboratoryService.deleteLabDTO(l);
        return ResultVO.success(Map.of("orderlab",l,"status", LabUtils.labList));
    }
    @ApiOperation("查询预约实验室的记录")
    @PostMapping("orderlabslist")
    public ResultVO orderLabslist() {

        return ResultVO.success(Map.of("labcourse",laboratoryService.getAllLab(),"status", LabUtils.labList));
    }
    @ApiOperation("实验室初始化")
    @PostMapping("initlabslist")
    public ResultVO initLabslist() {
        laboratoryService.initLaboratory();
        return ResultVO.success(Map.of("init", LabUtils.labList));
    }
}
