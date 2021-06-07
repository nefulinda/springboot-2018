package com.nefu.myspringboot.dto;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class StudentDTO {
    private Long sid;
    private String name;
    private String number;
    private String clazz;
    private String college;
}
