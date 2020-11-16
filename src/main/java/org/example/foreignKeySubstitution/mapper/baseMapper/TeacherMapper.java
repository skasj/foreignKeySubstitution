package org.example.foreignKeySubstitution.mapper.baseMapper;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.foreignKeySubstitution.mapper.providerUtils.InsertSQLGenerator;
import org.example.foreignKeySubstitution.modal.entity.Teacher;

@Mapper
public interface TeacherMapper {
//    int deleteByPrimaryKey(Integer id);

    @InsertProvider(type = InsertSQLGenerator.class,method = "insertRecord")
    int insert(@Param("record") Teacher record,@Param("tableName") String tableName);

//    int insertSelective(Teacher record);

    @Select("select id,name,telephone from teacher where id = #{record.id}")
    Teacher selectByPrimaryKey(Integer id);

//    int updateByPrimaryKeySelective(Teacher record);

//    int updateByPrimaryKey(Teacher record);
}