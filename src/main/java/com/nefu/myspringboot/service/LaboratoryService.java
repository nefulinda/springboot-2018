package com.nefu.myspringboot.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nefu.myspringboot.common.Hour;
import com.nefu.myspringboot.dto.LaboratoryDTO;
import com.nefu.myspringboot.dto.ScheduleDTO;
import com.nefu.myspringboot.entity.Laboratory;
import com.nefu.myspringboot.entity.Schedule;
import com.nefu.myspringboot.mapper.CourseMapper;
import com.nefu.myspringboot.mapper.LaboratoryMapper;
import com.nefu.myspringboot.mapper.ScheduleMapper;
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
    private ScheduleMapper scheduleMapper;

    //实验室初始化
    public void initLaboratory() {
        scheduleMapper.delete(null);
        List<Laboratory> laboratories = laboratoryMapper.selectList(null);
        for (Laboratory laboratory : laboratories) {
            for (int i = 1; i <= Hour.WEEK; i++) {
                for (int j = 1; j <= Hour.DAY; j++) {
                    for (int k = 1; k <= Hour.SECTION; k++) {
                        Schedule s = Schedule.builder()
                                .labId(laboratory.getNumber())
                                .week(Integer.toString(i))
                                .day(Integer.toString(j))
                                .section(Integer.toString(k))
                                .state(0)
                                .build();
                        scheduleMapper.insert(s);
                    }
                }
            }
        }
    }


    //预约实验室
    public void addLabDTO(LaboratoryDTO lab) {
        Laboratory l = selectLab(lab.getNumber());
        if (l != null) {
            for (ScheduleDTO s : lab.getSchedules()) {
                for (int i = 1; i <= Hour.WEEK; i++) {
                    for (int j = 1; j <= Hour.DAY; j++) {
                        for (int k = 1; k <= Hour.SECTION; k++) {
                            if (i == Integer.valueOf(s.getSchedule().getWeek())
                                    && j == Integer.valueOf(s.getSchedule().getDay())
                                    && k == Integer.valueOf(s.getSchedule().getSection())
                                    && scheduleMapper.state(lab.getNumber(), i, j, k) == 0) {
                                Schedule schedule = scheduleMapper.selectById(s.getSchedule().getId());
                                schedule.setState(1);
                                scheduleMapper.updateById(schedule);
                            }
                        }
                    }
                }
            }
        }
    }

    //取消预约
    public void deleteLabDTO(LaboratoryDTO lab) {
        for (ScheduleDTO s : lab.getSchedules()) {
            for (int i = 1; i <= Hour.WEEK; i++) {
                for (int j = 1; j <= Hour.DAY; j++) {
                    for (int k = 1; k <= Hour.SECTION; k++) {
                        if (i == Integer.valueOf(s.getSchedule().getWeek())
                                && j == Integer.valueOf(s.getSchedule().getDay())
                                && k == Integer.valueOf(s.getSchedule().getSection())
                                && scheduleMapper.state(lab.getNumber(), i, j, k) == 1) {
                            Schedule schedule = scheduleMapper.selectById(s.getSchedule().getId());
                            schedule.setState(0);
                            scheduleMapper.updateById(schedule);
                        }
                    }
                }
            }
        }
    }

    //查询单个实验室
    public Laboratory selectLab(long number) {
        QueryWrapper<Laboratory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("number", number);
        return laboratoryMapper.selectOne(queryWrapper);
    }

    //查询实验室课程
    public List<LaboratoryDTO> getAllSchedule() {
        return scheduleMapper.list();
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
                        Schedule s = Schedule.builder()
                                .labId(lab.getNumber())
                                .week(Integer.toString(i))
                                .day(Integer.toString(j))
                                .section(Integer.toString(k))
                                .build();
                        scheduleMapper.insert(s);
                    }
                }
            }
        }
    }

    //删除实验室
    public void deleteLab(int number) {
        QueryWrapper<Laboratory> l = new QueryWrapper<>();
        l.eq("number", number);
        Laboratory lab = laboratoryMapper.selectOne(l);
        if (lab != null) {
            QueryWrapper<Schedule> scheduleQueryWrapper = new QueryWrapper<>();
            scheduleQueryWrapper.eq("lab_id", number);
            scheduleMapper.delete(scheduleQueryWrapper);
            laboratoryMapper.delete(l);
        }
    }
}
