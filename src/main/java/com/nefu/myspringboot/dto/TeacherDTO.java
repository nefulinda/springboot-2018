package com.nefu.myspringboot.dto;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TeacherDTO {
    private String number;
    private String name;
    private String title;

}
