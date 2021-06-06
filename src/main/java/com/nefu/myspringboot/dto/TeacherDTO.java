package com.nefu.myspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {
    private Long tid;
    private String number;
    private String name;
    private String post;
    private String college;

}
