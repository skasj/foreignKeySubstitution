package org.example.foreignKeySubstitution.mapper.mysqlMapper;

import org.example.foreignKeySubstitution.mapper.baseMapper.TeacherCourseMapper;
import org.example.foreignKeySubstitution.modal.entity.TeacherCourse;

public interface TeacherCourseMysqlMapper extends TeacherCourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TeacherCourse record);

    int insertSelective(TeacherCourse record);

    TeacherCourse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TeacherCourse record);

    int updateByPrimaryKey(TeacherCourse record);
}