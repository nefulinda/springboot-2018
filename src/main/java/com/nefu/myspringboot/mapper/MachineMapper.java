package com.nefu.myspringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nefu.myspringboot.entity.Machine;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MachineMapper extends BaseMapper<Machine> {
    List<Machine> listMachine();
}
