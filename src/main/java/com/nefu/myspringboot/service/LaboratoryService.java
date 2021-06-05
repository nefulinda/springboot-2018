package com.nefu.myspringboot.service;

import com.nefu.myspringboot.dto.LaboratoryDTO;
import com.nefu.myspringboot.entity.Course;
import com.nefu.myspringboot.entity.LabCourse;
import com.nefu.myspringboot.entity.Laboratory;
import com.nefu.myspringboot.mapper.*;
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
    private StudentCourseMapper studentCourseMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private LaboratoryMapper laboratoryMapper;
    @Autowired
    private LaboratoryStudentMapper laboratoryStudentMapper;

    //实验室初始化
    public void initLaboratory() {
        LabUtils.initLab();
    }

    public boolean addLab(LaboratoryDTO lab) {
        Laboratory l = laboratoryMapper.selectById(lab.getLabId());
        if (l != null && l.getNumber() >= lab.getLabNumber()) {
            if (lab.getWeek() > 0 && lab.getDay() > 0 && lab.getClassHour() > 0) {
                //如果该段时间内的实验室被预约了 则终止
                for (int i = lab.getBeginWeek().intValue(); i <= lab.getEndWeek().intValue(); i++) {
                    for (int j = lab.getBeginDay().intValue(); j <= lab.getBeginDay().intValue(); j++) {
                        for (int k = lab.getBeginClassHour().intValue(); k <= lab.getEndClassHour().intValue(); k++) {
                            if (LabUtils.labList[i][j][k] == 1) {
                                return false;
                            }
                        }
                    }
                }
                for (int i = lab.getBeginWeek().intValue(); i <= lab.getEndWeek().intValue(); i++) {
                    for (int j = lab.getBeginDay().intValue(); j <= lab.getBeginDay().intValue(); j++) {
                        for (int k = lab.getBeginClassHour().intValue(); k <= lab.getEndClassHour().intValue(); k++) {
                            LabUtils.labList[i][j][k] = 1;
                        }
                    }
                }
            }
            //预约成功 实验室与课程建立关系
            LabCourse labCourse = LabCourse.builder()
                    .labId(lab.getLabId())
                    .id(lab.getLid())
                    .cid(lab.getCourseDTO().getCid())
                    .build();
            labCourseMapper.insert(labCourse);

            Course c = Course.builder()
                    .id(labCourse.getId())
                    .number(lab.getCourseDTO().getCid())
                    .name(lab.getCourseDTO().getName())
                    .teacherId(lab.getCourseDTO().getTeacherDTO().getTid())
                    .build();
            courseMapper.insert(c);
            return true;
        }
        return false;
    }

    public void delteLab(LaboratoryDTO lab) {

        LabCourse l = labCourseMapper.selectById(lab.getLid());
        if (l != null) {
            labCourseMapper.deleteById(lab.getLid());
            courseMapper.deleteById(l.getId());
            LabUtils.initLab(lab.getBeginWeek().intValue(), lab.getEndWeek().intValue(),
                    lab.getBeginDay().intValue(), lab.getBeginDay().intValue(),
                    lab.getBeginClassHour().intValue(), lab.getEndClassHour().intValue());
        }
    }

    public LabCourse selectLab(long lid) {

        return null;
    }

    public List<LabCourse> getAllLab() {
        return null;
    }
}
