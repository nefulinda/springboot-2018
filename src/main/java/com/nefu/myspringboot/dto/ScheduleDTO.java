package com.nefu.myspringboot.dto;

import com.nefu.myspringboot.entity.Course;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ScheduleDTO {
    private  String week;
    private String day;
    private String order;
    private Course course;
    private boolean status=false;
}

