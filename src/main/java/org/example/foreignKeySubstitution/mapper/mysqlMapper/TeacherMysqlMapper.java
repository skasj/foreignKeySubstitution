package org.example.foreignKeySubstitution.mapper.mysqlMapper;

import org.apache.ibatis.annotations.*;
import org.example.foreignKeySubstitution.mapper.baseMapper.TeacherMapper;
import org.example.foreignKeySubstitution.mapper.baseProvider.TeacherProvider;
import org.example.foreignKeySubstitution.modal.entity.Teacher;

import java.util.List;

@Mapper
public interface TeacherMysqlMapper extends TeacherMapper {
//    int deleteByPrimaryKey(Integer id);

    @InsertProvider(type = TeacherProvider.class, method = "insert")
    int insert(@Param("record") Teacher record);

    @Select("select id,name,telephone from teacher where id = #{record.id}")
    Teacher selectByPrimaryKey(Integer id);

    @SelectProvider(type = TeacherProvider.class, method = "selectByIdList")
    List<Teacher> selectByIdList(@Param("idList") List<Object> idList);

    @DeleteProvider(type = TeacherProvider.class, method = "deleteByIdList")
    Integer deleteByIdList(@Param("idList") List<Object> idList);

    @UpdateProvider(type = TeacherProvider.class, method = "batchUpdateById")
    Integer batchUpdateById(@Param("teacherList") List<Teacher> teacherList);
}