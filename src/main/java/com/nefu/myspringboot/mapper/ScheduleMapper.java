package com.nefu.myspringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nefu.myspringboot.dto.LaboratoryDTO;
import com.nefu.myspringboot.dto.ScheduleDTO;
import com.nefu.myspringboot.entity.Schedule;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ScheduleMapper extends BaseMapper<Schedule> {
    List<LaboratoryDTO> list();
    int state(int labId,int week,int day,int section);
}
