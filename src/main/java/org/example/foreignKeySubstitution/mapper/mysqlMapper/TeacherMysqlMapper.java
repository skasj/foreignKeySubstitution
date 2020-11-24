package org.example.foreignKeySubstitution.mapper.mysqlMapper;

import org.apache.ibatis.annotations.*;
import org.example.foreignKeySubstitution.mapper.baseMapper.TeacherMapper;
import org.example.foreignKeySubstitution.mapper.baseProvider.TeacherProvider;
import org.example.foreignKeySubstitution.modal.entity.Teacher;

import java.io.Serializable;
import java.util.List;

@Mapper
public interface TeacherMysqlMapper extends TeacherMapper, Serializable {

    @InsertProvider(type = TeacherProvider.class, method = "insert")
    int insert(@Param("record") Teacher record);

    @Select("select id,name,telephone from teacher where id = #{record.id}")
    Teacher selectByPrimaryKey(Integer id);

    @SelectProvider(type = TeacherProvider.class, method = "selectByIdList")
    List<Teacher> selectByIdList(@Param("idList") List<Object> idList);

    @DeleteProvider(type = TeacherProvider.class, method = "deleteByIdList")
    Integer deleteByIdList(@Param("idList") List<Object> idList);

    @Select("select id from teacher where name = #{name} and telephone= #{telephone}")
    List<Integer> selectIdListByNameAndTelephone(@Param("name") String name, @Param("telephone") String telephone);

    @Delete("delete from teacher where name=#{name} and telephone = #{telephone}")
    Integer deleteByNameAndTelephone(@Param("name") String name, @Param("telephone") String telephone);

    @UpdateProvider(type = TeacherProvider.class, method = "batchUpdateById")
    Integer batchUpdateById(@Param("teacherList") List<Teacher> teacherList);

    @SelectProvider(type = TeacherProvider.class, method = "countByIdList")
    Integer countByIdList(@Param("idList") List<Object> idList);
}