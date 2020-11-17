package org.example.foreignKeySubstitution.mapper.mysqlMapper;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.foreignKeySubstitution.mapper.baseMapper.ClassMapper;
import org.example.foreignKeySubstitution.mapper.baseProvider.ClassProvider;
import org.example.foreignKeySubstitution.modal.entity.Class;

@Mapper
public interface ClassMysqlMapper extends ClassMapper {
//    int deleteByPrimaryKey(Integer id);

    /**
     * @see ClassProvider#insert
     */
    @InsertProvider(type = ClassProvider.class)
    int insert(@Param("record") Class record);

//    int insertSelective(Class record);

//    /**
//     * @see ClassProvider#selectByPrimaryKey
//     */
//    @InsertProvider(type = ClassProvider.class)
//    Class selectByPrimaryKey(@Param("id") Integer id);

//    int updateByPrimaryKeySelective(Class record);

//    int updateByPrimaryKey(Class record);
}