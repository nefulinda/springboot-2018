package com.nefu.myspringboot.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    private Long cid;
    private String name;
    private TeacherDTO teacherDTO;
    private List<StudentDTO> studentDTOS;
}
