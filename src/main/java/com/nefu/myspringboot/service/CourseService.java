package com.nefu.myspringboot.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nefu.myspringboot.entity.Course;
import com.nefu.myspringboot.mapper.CourseMapper;
import com.nefu.myspringboot.mapper.StudentMapper;
import com.nefu.myspringboot.mapper.TeacherMapper;
import com.nefu.myspringboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private UserMapper userMapper;

    public void addCourse(Course course) {
        Course c = Course.builder()
                .cid(course.getCid())
                .name(course.getName())
                .hours(course.getHours())
                .teacherId(course.getTeacherId())
                .studentNumber(course.getStudentNumber())
                .build();
        courseMapper.insert(c);
    }

    public void updateCourse(Course course) {
       QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
       courseQueryWrapper.eq("cid",course.getCid());
       Course course1=  courseMapper.selectOne(courseQueryWrapper);
       course1.setStudentNumber(course.getStudentNumber());
       course1.setCid(course.getCid());
       course1.setHours(course.getHours());
       course1.setName(course.getName());
       course1.setTeacherId(course.getTeacherId());
       courseMapper.updateById(course1);
    }

    public void deleteCourse(String name) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        courseMapper.delete(queryWrapper);
    }

    //查询预约有的课程
    public List<Course> getAllCourses() {
        return courseMapper.selectList(null);
    }

    public List<Course> getCoursesByNumber(String number) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id", number);
        return courseMapper.selectList(queryWrapper);
    }


    public Course getCourse(long tid, long cid) {
        return courseMapper.getCourse(tid, cid);
    }
    //查询课程学生


}
