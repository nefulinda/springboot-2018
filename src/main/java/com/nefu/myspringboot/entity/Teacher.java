package com.nefu.myspringboot.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ApiModel(value = "教师类")
public class Teacher {
    @ApiModelProperty("教师编号")
    private Long id;
    @ApiModelProperty("职位")
    private String post;
    @ApiModelProperty("学院")
    private String college;
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private LocalDateTime createTime;

    @TableField(updateStrategy = FieldStrategy.NEVER)
    private LocalDateTime updateTime;

}
