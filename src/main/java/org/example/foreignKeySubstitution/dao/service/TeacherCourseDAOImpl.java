package org.example.foreignKeySubstitution.dao.service;

import org.example.foreignKeySubstitution.mapper.baseMapper.CourseMapper;
import org.example.foreignKeySubstitution.mapper.baseMapper.TeacherCourseMapper;
import org.example.foreignKeySubstitution.mapper.baseMapper.TeacherMapper;
import org.example.foreignKeySubstitution.modal.dto.TeacherCourseDTO;
import org.example.foreignKeySubstitution.modal.entity.Course;
import org.example.foreignKeySubstitution.modal.entity.Teacher;
import org.example.foreignKeySubstitution.modal.entity.TeacherCourse;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TeacherCourseDAOImpl implements TeacherCourseDAO {

    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private TeacherCourseMapper teacherCourseMapper;

    @Override
    public List<TeacherCourseDTO> selectByIdList(List<Object> idList) {
        List<TeacherCourse> teacherCourses = teacherCourseMapper.selectByIdList(idList);
        List<TeacherCourseDTO> teacherCourseDTOList = teacherCourses.stream()
                .map(this::teacherCourseConverter)
                .collect(Collectors.toList());
        // 此处有两张表，如果只有一张，可以使用HashMap进行优化
        List<Teacher> teacherList = teacherMapper.selectByIdList(teacherCourseDTOList.stream()
                .map(TeacherCourseDTO::getTeacherId)
                .collect(
                        Collectors.toList()));
        List<Course> courseList = courseMapper.selectByIdList(teacherCourseDTOList.stream()
                .map(TeacherCourseDTO::getCourseId)
                .collect(Collectors.toList()));
        for (TeacherCourseDTO dto : teacherCourseDTOList) {
            for (Teacher teacher : teacherList) {
                if (dto.getTeacherId()
                        .equals(teacher.getId())) {
                    dto.setTeacherName(teacher.getName());
                    dto.setTelephone(teacher.getTelephone());
                    break;
                }
            }
            for (Course course : courseList) {
                if (dto.getCourseId()
                        .equals(course.getId())) {
                    dto.setCourseName(course.getName());
                    break;
                }
            }
        }
        return teacherCourseDTOList;
    }

    private TeacherCourseDTO teacherCourseConverter(TeacherCourse teacherCourse) {
        TeacherCourseDTO teacherCourseDTO = new TeacherCourseDTO();
        teacherCourseDTO.setCourseId(teacherCourse.getCourseId());
        teacherCourseDTO.setId(teacherCourse.getId());
        teacherCourseDTO.setTeacherId(teacherCourse.getTeacherId());
        return teacherCourseDTO;
    }
}
