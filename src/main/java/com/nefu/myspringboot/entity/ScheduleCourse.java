package com.nefu.myspringboot.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ScheduleCourse {
    private Long id;
    private Long labId;
    @ApiModelProperty("课程编号")
    private Long cid;
    @ApiModelProperty("课程名称")
    private String name;
    @ApiModelProperty("该课程的老师编号")
    private Long teacherId;
    private int  week;
    private int  day;
    private int  section;
    private boolean state=true;

    @TableField(updateStrategy = FieldStrategy.NEVER)
    private LocalDateTime createTime;

    @TableField(updateStrategy = FieldStrategy.NEVER)
    private LocalDateTime updateTime;
    @Version
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private Integer version;
}
