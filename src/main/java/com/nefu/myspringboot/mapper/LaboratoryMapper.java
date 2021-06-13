package com.nefu.myspringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nefu.myspringboot.entity.Laboratory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaboratoryMapper extends BaseMapper<Laboratory> {
    List<Laboratory> listLab();

}
