package com.nefu.myspringboot.service;

import com.nefu.myspringboot.dto.LaboratoryDTO;
import com.nefu.myspringboot.entity.Course;
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


    //实验室初始化
    public void initLaboratory() {
        LabUtils.initLab();
    }

    //预约实验室
    public boolean addLab(LaboratoryDTO lab) {
        Laboratory l = laboratoryMapper.selectById(lab.getLabId());
        if (l != null) {

            for (int m = 0; m < lab.getCourses().size(); m++) {

                //如果该段时间内的实验室被预约了 则终止
                for (int i = lab.getCourses().get(0).getWeek().intValue(); i <= lab.getCourses().get(1).getWeek().intValue(); i++) {
                    for (int j = lab.getCourses().get(0).getDay().intValue(); j <= lab.getCourses().get(1).getDay().intValue(); j++) {
                        for (int k = lab.getCourses().get(0).getClassHour().intValue(); k <= lab.getCourses().get(1).getClassHour().intValue(); k++) {
                            if (LabUtils.labList[i][j][k] == 1) {
                                return false;
                            }
                        }
                    }
                }
                for (int i = lab.getCourses().get(0).getWeek().intValue(); i <= lab.getCourses().get(1).getWeek().intValue(); i++) {
                    for (int j = lab.getCourses().get(0).getDay().intValue(); j <= lab.getCourses().get(1).getDay().intValue(); j++) {
                        for (int k = lab.getCourses().get(0).getClassHour().intValue(); k <= lab.getCourses().get(1).getClassHour().intValue(); k++) {
                            LabUtils.labList[i][j][k] = 1;
                        }
                    }
                }
            }
//            //预约成功 实验室与课程建立关系
//            LabCourse labCourse = LabCourse.builder()
//                    .labId(lab.getLabId())
//                    .id(lab.getLid())
//                    .cid(lab.getCourses().get(0).getCid())
//                    .build();
//            labCourseMapper.insert(labCourse);

            Course c = Course.builder()
                    .id(lab.getCourses().get(0).getCid())
                    .name(lab.getCourses().get(0).getName())
                    .teacherId(lab.getCourses().get(0).getTeacherDTO().getTid())
                    .build();
            courseMapper.insert(c);
            return true;
        }

//                if ( > 0 && lab.getDay() > 0 && lab.getClassHour() > 0) {
//                    //如果该段时间内的实验室被预约了 则终止
//                    for (int i = lab.getBeginWeek().intValue(); i <= lab.getEndWeek().intValue(); i++) {
//                        for (int j = lab.getBeginDay().intValue(); j <= lab.getBeginDay().intValue(); j++) {
//                            for (int k = lab.getBeginClassHour().intValue(); k <= lab.getEndClassHour().intValue(); k++) {
//                                if (LabUtils.labList[i][j][k] == 1) {
//                                    return false;
//                                }
//                            }
//                        }
//                    }
//                    for (int i = lab.getBeginWeek().intValue(); i <= lab.getEndWeek().intValue(); i++) {
//                        for (int j = lab.getBeginDay().intValue(); j <= lab.getBeginDay().intValue(); j++) {
//                            for (int k = lab.getBeginClassHour().intValue(); k <= lab.getEndClassHour().intValue(); k++) {
//                                LabUtils.labList[i][j][k] = 1;
//                            }
//                        }
//                    }
//                }
//                //预约成功 实验室与课程建立关系
//                LabCourse labCourse = LabCourse.builder()
//                        .labId(lab.getLabId())
//                        .id(lab.getLid())
//                        .cid(lab.getCourseDTO().getCid())
//                        .build();
//                labCourseMapper.insert(labCourse);
//
//                Course c = Course.builder()
//                        .id(lab.getCourseDTO().getCid())
//                        .name(lab.getCourseDTO().getName())
//                        .teacherId(lab.getCourseDTO().getTeacherDTO().getTid())
//                        .build();
//                courseMapper.insert(c);
//                return true;
//            }
        return false;
    }

    //取消预约
    public void delteLab(LaboratoryDTO lab) {

        LabCourse l = labCourseMapper.selectById(lab.getLid());
        if (l != null) {
            for (int m = 0; m < lab.getCourses().size(); m++) {
                labCourseMapper.deleteById(lab.getLid());
                courseMapper.deleteById(lab.getCourses().get(0).getCid());
            }
            LabUtils.initLab(lab.getCourses().get(0).getWeek().intValue(), lab.getCourses().get(1).getWeek().intValue(),
                    lab.getCourses().get(0).getDay().intValue(), lab.getCourses().get(0).getDay().intValue(),
                    lab.getCourses().get(0).getClassHour().intValue(), lab.getCourses().get(0).getClassHour().intValue());
        }
    }


    //查询单个实验室
    public Laboratory selectLab(Laboratory laboratory) {

        return laboratoryMapper.selectById(laboratory.getId());
    }

    //查询预约实验室的记录
    public List<LabCourse> getAllLab() {
        return labCourseMapper.listCourse();
    }
}
