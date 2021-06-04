package com.nefu.myspringboot.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class LaboratoryDTO {
    private long lid;
    private CourseDTO  courseDTO;
    private Integer beginWeek;
    private Integer endWeek;
    private Integer beginDay;
    private Integer endDay;
    private Integer beginTime;
    private Integer endTime;
}
