package com.nefu.myspringboot.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Machine {
    private Integer mid;
    private String mname;
    private String mnumber;
    private String mtype;
    private String mstate;
    private String muser;
    private LocalDateTime createTime;
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private LocalDateTime updateTime;
    @Version
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private Integer version;
}
