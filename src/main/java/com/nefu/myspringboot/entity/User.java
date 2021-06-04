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
@ApiModel(value = "user")
public class User {
    @ApiModelProperty("编号")
    private Long id;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("学号/工号")
    private String number;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("权限")
    private Integer role;

    @TableField(updateStrategy = FieldStrategy.NEVER)
    private LocalDateTime createTime;

    @TableField(updateStrategy = FieldStrategy.NEVER)
    private LocalDateTime updateTime;
    @Version
    private  Integer version;
}
