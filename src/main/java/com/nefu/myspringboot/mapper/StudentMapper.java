package com.nefu.myspringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nefu.myspringboot.dto.StudentDTO;
import com.nefu.myspringboot.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper extends BaseMapper<Student> {
    List<StudentDTO> list();
}
