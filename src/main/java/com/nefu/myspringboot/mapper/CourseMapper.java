package com.nefu.myspringboot.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nefu.myspringboot.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CourseMapper extends BaseMapper<Course> {
 //   List<Course> listBySid(long sid);
    List<Course> getCoursesByNumber( String number);

//    default List<Course> listByTid(String tid) {
//        return selectList(new LambdaQueryWrapper<Course>()
//                .eq(Course::getTeacherId, tid));
//    }

    default Course getCourse(long tid, long cid) {
        return selectOne(new LambdaQueryWrapper<Course>()
                .eq(Course::getId, cid)
                .eq(Course::getTeacherId, tid));
    }
}
