package com.nefu.myspringboot.controller;


import com.nefu.myspringboot.dto.TeacherDTO;
import com.nefu.myspringboot.entity.*;
import com.nefu.myspringboot.service.*;
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
    @Autowired
    private MachineService machineService;

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
    public ResultVO deleteTeacher(@RequestBody TeacherDTO teacher) {
        userService.deleteTeacher(teacher.getNumber());
        return ResultVO.success(Map.of("teachernumber", teacher.getNumber()));
    }

    @ApiOperation("修改教师信息")
    @PostMapping("modifyteacher")
    public ResultVO modifyTeacher(@RequestBody TeacherDTO teacher) {
        userService.updateTeacher(teacher);
        return ResultVO.success(Map.of("update", teacher));
    }

    @ApiOperation("通过名字获取教师")
    @PostMapping("getusersname")
    public ResultVO getTeacherByname(@RequestBody TeacherDTO teacher) {
        List<User> users = userService.getUserByName(teacher.getName());
        return ResultVO.success(Map.of("user", users));
    }

    @ApiOperation("增加实验室")
    @PostMapping("addlabs")
    public ResultVO addLab(@RequestBody Laboratory laboratory) {
        laboratoryService.addLab(laboratory);
        return ResultVO.success(Map.of("addlabs", laboratory));
    }

    @ApiOperation("删除实验室")
    @PostMapping("deletelab")
    public ResultVO deleteLab(@RequestBody Laboratory laboratory) {
        laboratoryService.deleteLab(laboratory.getNumber());
        return ResultVO.success(Map.of("deletelab", laboratory.getNumber()));
    }

    @ApiOperation("修改实验室")
    @PostMapping("updatelab")
    public ResultVO updateLab(@RequestBody Laboratory laboratory) {
        laboratoryService.updateLab(laboratory);
        return ResultVO.success(Map.of("updatelab", laboratoryService.getListLab()));
    }

    @ApiOperation("获取全部课程")
    @GetMapping("getallcourses")
    public ResultVO getCourses() {
        return ResultVO.success(Map.of("allCourse", courseServicer.getAllCourses()));
    }


//    @ApiOperation("获取指定老师的课程 ")
//    @GetMapping("gettidcourse")
//    public ResultVO getTeacherCourse(@RequestAttribute(value = "uid") long uid) {
//        return ResultVO.success(Map.of("gettidcourse",courseServicer.listCoursesByTid(uid)));
//    }


    @ApiOperation("实验室初始化")
    @PostMapping("initlabslist")
    public ResultVO initLabslist() {
        laboratoryService.initLaboratory();
        return ResultVO.success(Map.of());
    }

    @ApiOperation("增加公告")
    @PostMapping("addnotice")
    public ResultVO addNotice(@RequestBody Notice notice) {
        noticeService.addNotice(notice);
        return ResultVO.success(Map.of("addnotice", noticeService.getAllNotice()));
    }

    @ApiOperation("删除公告")
    @PostMapping("delnotice")
    public ResultVO delNotice(@RequestBody Notice notice) {
        noticeService.deleteNotice(notice);
        return ResultVO.success(Map.of("delnotice", noticeService.getAllNotice()));
    }

    @ApiOperation("修改公告")
    @PostMapping("updatenotice")
    public ResultVO updateNotice(@RequestBody Notice notice) {
        noticeService.updateNotice(notice);
        return ResultVO.success(Map.of("updatenotice", noticeService.getAllNotice()));
    }

    @ApiOperation("获取全部通知公告")
    @GetMapping("notice")
    public ResultVO getNotice() {
        return ResultVO.success(Map.of("notice", noticeService.getAllNotice()));
    }

    @ApiOperation("增加设配")
    @PostMapping("addmachine")
    public ResultVO addMachine(@RequestBody Machine machine) {
     machineService.addMachine(machine);
        return ResultVO.success(Map.of("addmachine", machineService.machineList()));
    }

    @ApiOperation("删除设配")
    @PostMapping("delmachine")
    public ResultVO delMachine(@RequestBody Machine machine) {
      machineService.deleteMachine(machine);
        return ResultVO.success(Map.of("delmachine", machineService.machineList()));
    }

    @ApiOperation("修改设配信息")
    @PostMapping("updatemachine")
    public ResultVO updateMachine(@RequestBody Machine machine) {
       machineService.updateMachine(machine);
        return ResultVO.success(Map.of("updatemachine", machineService.machineList()));
    }

    @ApiOperation("获取全部设配信息")
    @GetMapping("machines")
    public ResultVO getMachines() {
        return ResultVO.success(Map.of("machines", machineService.machineList()));
    }


}
