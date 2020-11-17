package org.example.foreignKeySubstitution.mapper.mysqlMapper;

import org.apache.ibatis.annotations.*;
import org.example.foreignKeySubstitution.mapper.baseMapper.TeacherCourseMapper;
import org.example.foreignKeySubstitution.mapper.baseProvider.TeacherCourseProvider;
import org.example.foreignKeySubstitution.modal.entity.TeacherCourse;

import java.util.List;

@Mapper
public interface TeacherCourseMysqlMapper extends TeacherCourseMapper {
    @InsertProvider(type = TeacherCourseProvider.class, method = "insert")
    int insert(@Param("record") TeacherCourse record);

    @Select("select id,teacher_id,course_id from teacher_course where id = #{id}")
    TeacherCourse selectByPrimaryKey(@Param("id") Integer id);

    @SelectProvider(type = TeacherCourseProvider.class, method = "selectByIdList")
    List<TeacherCourse> selectByIdList(@Param("idList") List<Object> idList);

    @DeleteProvider(type = TeacherCourseProvider.class, method = "deleteByIdList")
    int deleteByIdList(@Param("idList") List<Object> idList);
}