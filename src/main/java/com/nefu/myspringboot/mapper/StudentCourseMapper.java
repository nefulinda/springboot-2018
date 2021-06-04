package com.nefu.myspringboot.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nefu.myspringboot.entity.StudentCourse;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCourseMapper extends BaseMapper<StudentCourse> {
    default StudentCourse getStudentCourse(long sid, long cid) {
        return selectOne(new LambdaQueryWrapper<StudentCourse>()
                .eq(StudentCourse::getCourseId, cid)
                .eq(StudentCourse::getStudentId, sid));
    }
}
