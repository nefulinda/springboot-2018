package com.nefu.myspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Long sid;
    private String name;
    private String number;
    private String clazz;
    private String college;
}
