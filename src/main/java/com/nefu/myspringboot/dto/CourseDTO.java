package com.nefu.myspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    private String number;
    private TeacherDTO teacherDTO;
    private List<StudentDTO> studentDTOS;
}
