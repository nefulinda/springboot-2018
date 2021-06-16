package com.nefu.myspringboot.dto;

import com.nefu.myspringboot.entity.Course;
import com.nefu.myspringboot.entity.Schedule;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ScheduleDTO {
    private Schedule schedule;
    private Course course;
}
