package org.example.foreignKeySubstitution.mapper.baseMapper;

import org.example.foreignKeySubstitution.modal.entity.TeacherCourse;

public interface TeacherCourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TeacherCourse record);

    int insertSelective(TeacherCourse record);

    TeacherCourse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TeacherCourse record);

    int updateByPrimaryKey(TeacherCourse record);
}