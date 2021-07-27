package com.nefu.myspringboot.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nefu.myspringboot.common.Hour;
import com.nefu.myspringboot.dto.LaboratoryDTO;
import com.nefu.myspringboot.entity.Laboratory;
import com.nefu.myspringboot.entity.ScheduleCourse;
import com.nefu.myspringboot.mapper.LaboratoryMapper;
import com.nefu.myspringboot.mapper.ScheduleCourseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@Slf4j
public class LaboratoryService {
    @Autowired
    private LaboratoryMapper laboratoryMapper;

    @Autowired
    private ScheduleCourseMapper scheduleCourseMapper;

    //实验室初始化
    public void initLaboratory() {
        scheduleCourseMapper.delete(null);
        List<Laboratory> laboratories = laboratoryMapper.selectList(null);
        for (Laboratory laboratory : laboratories) {
            for (int i = 1; i <= Hour.WEEK; i++) {
                for (int j = 1; j <= Hour.DAY; j++) {
                    for (int k = 1; k <= Hour.SECTION; k++) {
                        ScheduleCourse s = ScheduleCourse.builder()
                                .labId(laboratory.getNumber())
                                .week(i)
                                .name("无")
                                .day(j)
                                .section(k)
                                .state(true)
                                .build();
                        scheduleCourseMapper.insert(s);
                    }
                }
            }
        }
    }


    //预约实验室
    public void addLabDTO(LaboratoryDTO lab) {
        Laboratory l = selectLab(lab.getNumber());
        if (l != null) {
            for (ScheduleCourse s : lab.getSchedule()) {
                if (scheduleCourseMapper.state(lab.getNumber(), s.getWeek(), s.getDay(), s.getSection()))
                scheduleCourseMapper.updateSchedule(lab.getNumber(), s.getWeek(), s.getDay(), s.getSection(), s.isState(), s.getTeacherId(), s.getName());
//                for (int i = 1; i <= Hour.WEEK; i++) {
//                    for (int j = 1; j <= Hour.DAY; j++) {
//                        for (int k = 1; k <= Hour.SECTION; k++) {
//                                scheduleCourseMapper.updateSchedule(lab.getNumber(), i, j, k, s.isState(),s.getTeacherId(),s.getName());
//                            }
//                        }
//                    }
            }
        }

    }
//
//    //取消预约
//    public void deleteLabDTO(LaboratoryDTO lab) {
//        for (ScheduleCourse s : lab.getSchedule()) {
//            for (int i = 1; i <= Hour.WEEK; i++) {
//                for (int j = 1; j <= Hour.DAY; j++) {
//                    for (int k = 1; k <= Hour.SECTION; k++) {
//                            scheduleCourseMapper.updateSchedule(lab.getNumber(), i, j, k, ,0,s.getName());
//                    }
//                }
//            }
//        }
//    }

    //查询单个实验室
    public Laboratory selectLab(long number) {
        QueryWrapper<Laboratory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("number", number);
        return laboratoryMapper.selectOne(queryWrapper);
    }

    //查询实验室课程
    public List<LaboratoryDTO> getAllSchedule() {
        return scheduleCourseMapper.listCourses();
    }

    //获取实验室列表
    public List<Laboratory> getListLab() {
        return laboratoryMapper.listLab();
    }

    //增加实验室
    public void addLab(Laboratory laboratory) {

        QueryWrapper<Laboratory> l = new QueryWrapper<>();
        l.eq("number", laboratory.getNumber());
        Laboratory lab = laboratoryMapper.selectOne(l);
        if (lab == null) {
            laboratoryMapper.insert(laboratory);
            for (int i = 1; i <= Hour.WEEK; i++) {
                for (int j = 1; j <= Hour.DAY; j++) {
                    for (int k = 1; k <= Hour.SECTION; k++) {
                        ScheduleCourse s = ScheduleCourse.builder()
                                .labId(laboratory.getNumber())
                                .week(i)
                                .name("无")
                                .state(true)
                                .day(j)
                                .section(k)
                                .build();
                        scheduleCourseMapper.insert(s);
                    }
                }
            }
        }
    }

    //删除实验室
    public void deleteLab(long number) {
        QueryWrapper<Laboratory> l = new QueryWrapper<>();
        l.eq("number", number);
        Laboratory lab = laboratoryMapper.selectOne(l);
        if (lab != null) {
            QueryWrapper<ScheduleCourse> scheduleQueryWrapper = new QueryWrapper<>();
            scheduleQueryWrapper.eq("lab_id", number);
            scheduleCourseMapper.delete(scheduleQueryWrapper);
            laboratoryMapper.delete(l);
        }
    }

    //修改实验室
    public void updateLab(Laboratory laboratory) {
        QueryWrapper<Laboratory> l = new QueryWrapper<>();
        l.eq("number", laboratory.getNumber());
        Laboratory lab = laboratoryMapper.selectOne(l);
        lab.setComputerNumber(laboratory.getComputerNumber());
        laboratoryMapper.updateById(lab);

    }
}
