package com.nefu.myspringboot.entity;


import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.*;

import java.time.LocalDateTime;
@Setter
@Getter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
@TableName(value = "laboratory_student")
public class LaboratoryStudent {
    private Long id;
    private String name;
    private Long studentId;
    private Long laboratoryId;
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private LocalDateTime createTime;

    @TableField(updateStrategy = FieldStrategy.NEVER)
    private LocalDateTime updateTime;
    @Version
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private  Integer version;
}
