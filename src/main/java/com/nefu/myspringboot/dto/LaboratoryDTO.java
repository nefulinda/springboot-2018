package com.nefu.myspringboot.dto;


import lombok.*;

import java.util.List;


@Setter
@Getter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class LaboratoryDTO {
    private Long lid;
    private Long labId;
    private Integer labNumber;
    private List<CourseDTO> courses;


//    private Integer beginWeek;
//    private Integer endWeek;
//    private Integer beginDay;
//    private Integer endDay;
//    private Integer beginClassHour;
//    private Integer endClassHour;
//
//    private Integer week=this.endWeek-this.beginWeek+1;
//    private Integer day=this.endDay-this.beginDay+1;
//    private Integer  classHour=this.endClassHour-this.beginClassHour+1;
}
