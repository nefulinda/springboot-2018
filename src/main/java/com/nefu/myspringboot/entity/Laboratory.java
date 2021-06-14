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
@ApiModel(value = "实验室")
public class Laboratory {
    @ApiModelProperty("实验室编号")
    private Long number;
    @ApiModelProperty("实验室机器数量")
    private  Integer computerNumber;
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private LocalDateTime createTime;

    @TableField(updateStrategy = FieldStrategy.NEVER)
    private LocalDateTime updateTime;
    @Version
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private  Integer version;
}
