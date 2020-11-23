package org.example.foreignKeySubstitution.mapper.baseMapper;

import org.example.foreignKeySubstitution.modal.entity.ClassScheduleCard;

import java.util.List;


public interface ClassScheduleCardMapper {

    int insert(ClassScheduleCard record);

    List<ClassScheduleCard> selectByIdList(List<Object> id);

    Integer deleteByIdList(List<Object> idList);

    Integer deleteByTeacherCourseIdList(List<Object> idList);

}