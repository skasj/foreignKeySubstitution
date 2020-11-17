package org.example.foreignKeySubstitution.mapper.mysqlMapper;

import org.apache.ibatis.annotations.InsertProvider;
import org.example.foreignKeySubstitution.mapper.baseMapper.ClassScheduleCardMapper;
import org.example.foreignKeySubstitution.mapper.providerUtils.InsertSQLAssembler;
import org.example.foreignKeySubstitution.modal.entity.ClassScheduleCard;


public interface ClassScheduleCardMysqlMapper extends ClassScheduleCardMapper {
    int deleteByPrimaryKey(Integer id);

    @InsertProvider(type = InsertSQLAssembler.class,method = "insertRecord")
    int insert(ClassScheduleCard record);

    int insertSelective(ClassScheduleCard record);

    ClassScheduleCard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClassScheduleCard record);

    int updateByPrimaryKey(ClassScheduleCard record);
}