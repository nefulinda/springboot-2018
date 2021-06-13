package com.nefu.myspringboot.service;

import com.nefu.myspringboot.dto.CourseDTO;
import com.nefu.myspringboot.dto.StudentDTO;
import com.nefu.myspringboot.entity.Course;
import com.nefu.myspringboot.mapper.CourseMapper;
import com.nefu.myspringboot.mapper.StudentMapper;
import com.nefu.myspringboot.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

    public void addCourse(Course course, long uid) {
        course.setId(null);
        course.setTeacherId(uid);
        courseMapper.insert(course);
    }

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
    //查询课程学生


}
