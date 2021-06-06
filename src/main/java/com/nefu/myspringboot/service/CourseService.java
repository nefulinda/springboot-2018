package com.nefu.myspringboot.service;

import com.nefu.myspringboot.dto.CourseDTO;
import com.nefu.myspringboot.entity.Course;
import com.nefu.myspringboot.mapper.CourseMapper;
import com.nefu.myspringboot.mapper.StudentMapper;
import com.nefu.myspringboot.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    //查询预约有的课程
    public List<Course> getAllCourses() {
        return courseMapper.selectList(null);
    }


    public List<Course> listCoursesByTid(long uid) {
        return courseMapper.listByTid(uid);
    }

    public List<Course> listCoursesBySid(long sid) {
        return courseMapper.listBySid(sid);
    }

    public Course getCourse(long tid, long cid) {
        return courseMapper.getCourse(tid, cid);
    }
}
