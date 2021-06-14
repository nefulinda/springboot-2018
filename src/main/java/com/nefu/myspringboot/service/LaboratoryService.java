package com.nefu.myspringboot.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nefu.myspringboot.common.Hour;
import com.nefu.myspringboot.dto.LaboratoryDTO;
import com.nefu.myspringboot.entity.LabCourse;
import com.nefu.myspringboot.entity.Laboratory;
import com.nefu.myspringboot.mapper.CourseMapper;
import com.nefu.myspringboot.mapper.LabCourseMapper;
import com.nefu.myspringboot.mapper.LaboratoryMapper;
import com.nefu.myspringboot.utils.LabUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional

public class LaboratoryService {
    @Autowired
    private LabCourseMapper labCourseMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private LaboratoryMapper laboratoryMapper;

    private LaboratoryDTO laboratoryDTO;

    //实验室初始化
    public void initLaboratory() {
        LabUtils.initLab();
    }

    //预约实验室
    public boolean addLabDTO(LaboratoryDTO lab) {

        Laboratory l = selectLab(lab.getNumber());
        if (l != null) {
            for (int m = 0; m < lab.getSchedules().size(); m++) {
                //如果该段时间内的实验室被预约了 则终止
                for (int i = 1; i < Hour.WEEK; i++) {
                    for (int j = 1; j < Hour.DAY; j++) {
                        for (int k = 1; k < Hour.SECTION; k++) {
                            if (Integer.valueOf(lab.getSchedules().get(m).getWeek()) == i
                                    && Integer.valueOf(lab.getSchedules().get(m).getDay()) == j
                                    && Integer.valueOf(lab.getSchedules().get(m).getOrder()).intValue() == j
                                    && lab.getSchedules().get(m).isStatus() == true) {
                                return false;
                            }
                        }
                    }
                }
                for (int i = 1; i < Hour.WEEK; i++) {
                    for (int j = 1; j < Hour.DAY; j++) {
                        for (int k = 1; k < Hour.SECTION; k++) {
                            if (Integer.valueOf(lab.getSchedules().get(m).getWeek()) == i
                                    && Integer.valueOf(lab.getSchedules().get(m).getDay()) == j
                                    && Integer.valueOf(lab.getSchedules().get(m).getOrder()).intValue() == j
                                    && lab.getSchedules().get(m).isStatus() == false) {
                                LabCourse labCourse = LabCourse.builder()
                                        .cid(lab.getSchedules().get(m).getCourse().getId())
                                        .labId(lab.getNumber())
                                        .week(Integer.toString(i))
                                        .day(Integer.toString(j))
                                        .section(Integer.toString(k))
                                        .build();
                                labCourseMapper.insert(labCourse);
                            }
                        }
                    }
                }
                courseMapper.insert(lab.getSchedules().get(0).getCourse());
            }
            return true;
        }

        return false;
    }

    //取消预约
    public void deleteLabDTO(LaboratoryDTO lab) {

        LabCourse l = labCourseMapper.selectById(lab.getLid());
        if (l != null) {
            courseMapper.deleteById(lab.getSchedules().get(0).getCourse().getId());
            QueryWrapper<LabCourse> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("cid", lab.getSchedules().get(0).getCourse().getId());
            labCourseMapper.delete(queryWrapper);
            for (int m = 0; m < lab.getSchedules().size(); m++) {
                for (int i = 1; i < Hour.WEEK; i++) {
                    for (int j = 1; j < Hour.DAY; j++) {
                        for (int k = 1; k < Hour.SECTION; k++) {
                            if (Integer.valueOf(lab.getSchedules().get(m).getWeek()) == i
                                    && Integer.valueOf(lab.getSchedules().get(m).getDay()) == j
                                    && Integer.valueOf(lab.getSchedules().get(m).getOrder()).intValue() == j
                                    && lab.getSchedules().get(m).isStatus() == true) {
                                lab.getSchedules().get(m).setStatus(false);
                            }
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

    //查询预约实验室的记录
    public List<LabCourse> getAllLab() {
        return labCourseMapper.listCourse();
    }

    //获取实验室
    public List<LaboratoryDTO> getListLab() {
        return laboratoryMapper.listLab();
    }

    //增加实验室
    public void addLab(Laboratory laboratory) {
        Laboratory l = Laboratory.builder()
                .number(laboratory.getNumber())
                .computerNumber(laboratory.getComputerNumber())
                .build();
        laboratoryMapper.insert(l);

    }

    //删除实验室
    public void deleteLab(String name) {
        QueryWrapper<Laboratory> l = new QueryWrapper<>();
        l.eq("name", name);
        laboratoryMapper.delete(l);
    }


}
