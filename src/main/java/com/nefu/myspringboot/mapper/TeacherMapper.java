package com.nefu.myspringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nefu.myspringboot.dto.TeacherDTO;
import com.nefu.myspringboot.entity.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherMapper extends BaseMapper<Teacher> {
    List<TeacherDTO> list();
}
