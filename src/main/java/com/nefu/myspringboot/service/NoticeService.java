package com.nefu.myspringboot.service;

import com.nefu.myspringboot.entity.Notice;
import com.nefu.myspringboot.mapper.NoticeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;
    public void  updateNotice(Notice notice){
        noticeMapper.updateById(notice);
    }
    public Notice selectNotice(long id){ return noticeMapper.selectById(id);}
    //添加公告
    public void addNotice(Notice notice){
        noticeMapper.insert(notice);
    }
    //删除公告
    public void deleteNotice(Notice notice){
        noticeMapper.deleteById(notice.getId());
    }
    //查询公告
    public List<Notice> getAllNotice(){
        return noticeMapper.list();
    }
}
