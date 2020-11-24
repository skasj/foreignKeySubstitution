package org.example.foreignKeySubstitution.mapper.mysqlMapper;

import org.apache.ibatis.annotations.*;
import org.example.foreignKeySubstitution.mapper.baseMapper.CourseMapper;
import org.example.foreignKeySubstitution.mapper.baseProvider.CourseProvider;
import org.example.foreignKeySubstitution.modal.entity.Course;

import java.util.List;

@Mapper
public interface CourseMysqlMapper extends CourseMapper {

    @DeleteProvider(type = CourseProvider.class, method = "deleteByIdList")
    int deleteByIdList(@Param("idList") List<Integer> idList);

    @InsertProvider(type = CourseProvider.class, method = "insert")
    int insert(@Param("record") Course record);

    @Select("select id,name from course where id = #{id}")
    Course selectByPrimaryKey(@Param("id") Integer id);

    @SelectProvider(type = CourseProvider.class, method = "selectByIdList")
    List<Course> selectByIdList(@Param("idList") List<Integer> idList);

    @SelectProvider(type = CourseProvider.class, method = "countByIdList")
    Integer countByIdList(@Param("idList") List<Object> idList);
}