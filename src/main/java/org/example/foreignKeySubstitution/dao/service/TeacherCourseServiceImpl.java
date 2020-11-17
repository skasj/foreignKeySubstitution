package org.example.foreignKeySubstitution.dao.service;

import org.example.foreignKeySubstitution.mapper.baseMapper.CourseMapper;
import org.example.foreignKeySubstitution.mapper.baseMapper.TeacherCourseMapper;
import org.example.foreignKeySubstitution.mapper.baseMapper.TeacherMapper;
import org.example.foreignKeySubstitution.modal.dto.TeacherCourseDTO;
import org.example.foreignKeySubstitution.modal.entity.TeacherCourse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TeacherCourseServiceImpl implements TeacherCourseService{

    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private TeacherCourseMapper teacherCourseMapper;

    @Override
    public TeacherCourseDTO selectByIdList(List<Object> idList) {
        List<TeacherCourse> teacherCourses = teacherCourseMapper.selectByIdList(idList);
        return null;
    }

    private List<TeacherCourseDTO> teacherCourseConvertor(TeacherCourse teacherCourse){
        TeacherCourseDTO teacherCourseDTO = new TeacherCourseDTO();
        teacherCourse.setCourseId(teacherCourse.getCourseId());
        return null;
    }
}
