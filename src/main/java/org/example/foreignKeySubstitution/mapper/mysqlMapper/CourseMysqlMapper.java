package org.example.foreignKeySubstitution.mapper.mysqlMapper;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.foreignKeySubstitution.mapper.baseMapper.CourseMapper;
import org.example.foreignKeySubstitution.mapper.providerUtils.InsertSQLGenerator;
import org.example.foreignKeySubstitution.modal.entity.Course;

@Mapper
public interface CourseMysqlMapper extends CourseMapper {
    int deleteByPrimaryKey(Integer id);

    @InsertProvider(type = InsertSQLGenerator.class,method = "insertRecord")
    int insert(@Param("record") Course record,@Param("tableName") String tableName);

    int insertSelective(Course record);

    @Select("select id,name from course where id = #{record.id}")
    Course selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);
}