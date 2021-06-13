package com.nefu.myspringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nefu.myspringboot.entity.Notice;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NoticeMapper extends BaseMapper<Notice> {
    List<Notice> list();
}
