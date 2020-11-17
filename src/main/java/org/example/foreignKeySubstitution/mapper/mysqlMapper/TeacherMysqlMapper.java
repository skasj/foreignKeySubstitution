package org.example.foreignKeySubstitution.mapper.mysqlMapper;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.foreignKeySubstitution.mapper.baseMapper.TeacherMapper;
import org.example.foreignKeySubstitution.mapper.providerUtils.InsertSQLAssembler;
import org.example.foreignKeySubstitution.modal.entity.Teacher;

@Mapper
public interface TeacherMysqlMapper extends TeacherMapper {
//    int deleteByPrimaryKey(Integer id);

    @InsertProvider(type = InsertSQLAssembler.class,method = "insertRecord")
    int insert(@Param("record") Teacher record,@Param("tableName") String tableName);

//    int insertSelective(Teacher record);

    @Select("select id,name,telephone from teacher where id = #{record.id}")
    Teacher selectByPrimaryKey(Integer id);

//    int updateByPrimaryKeySelective(Teacher record);

//    int updateByPrimaryKey(Teacher record);
}