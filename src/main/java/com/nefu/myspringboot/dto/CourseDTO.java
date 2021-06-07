package com.nefu.myspringboot.dto;


import com.nefu.myspringboot.entity.Teacher;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CourseDTO {
    private Long cid;
    //课程名
    private String name;
    private Integer week;
    private Integer day;
    private Integer  classHour;
    private Teacher teacher;
    private boolean state = false;
   private List<StudentDTO> students;
}
