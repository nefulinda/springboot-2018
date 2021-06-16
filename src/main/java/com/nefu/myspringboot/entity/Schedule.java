package com.nefu.myspringboot.entity;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Schedule {
    private Long id;
    private Integer labId;
    //课程编号
    private Long cid;
    private String week;
    private String day;
    private String section;
    private Integer state;
    @Version
    private Integer version;
}

