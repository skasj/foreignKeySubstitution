package org.example.foreignKeySubstitution.mapper.baseMapper;

import org.example.foreignKeySubstitution.modal.entity.ClassScheduleCard;


public interface ClassScheduleCardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClassScheduleCard record);

    int insertSelective(ClassScheduleCard record);

    ClassScheduleCard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClassScheduleCard record);

    int updateByPrimaryKey(ClassScheduleCard record);
}