package com.nefu.myspringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nefu.myspringboot.dto.LaboratoryDTO;
import com.nefu.myspringboot.entity.LabCourse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabCourseMapper extends BaseMapper<LabCourse> {
    List<LabCourse> listCourse();
}
