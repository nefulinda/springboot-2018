package com.nefu.myspringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nefu.myspringboot.dto.LaboratoryDTO;
import com.nefu.myspringboot.entity.ScheduleCourse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ScheduleCourseMapper extends BaseMapper<ScheduleCourse> {
    List<LaboratoryDTO> listCourses();
    boolean state(@Param("labId") long labId, @Param("week")int week, @Param("day")int day, @Param("section")int section);
    int updateSchedule(@Param("labId") long labId, @Param("week")int week, @Param("day")int day, @Param("section")int section,@Param("state") boolean state,
                       @Param("teacherId")long teacherId,@Param("name")String name);
}
