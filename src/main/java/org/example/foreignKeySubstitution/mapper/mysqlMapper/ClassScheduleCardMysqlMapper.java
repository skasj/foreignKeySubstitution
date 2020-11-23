package org.example.foreignKeySubstitution.mapper.mysqlMapper;

import org.apache.ibatis.annotations.*;
import org.example.foreignKeySubstitution.mapper.baseMapper.ClassScheduleCardMapper;
import org.example.foreignKeySubstitution.mapper.baseProvider.ClassScheduleCardProvider;
import org.example.foreignKeySubstitution.modal.entity.ClassScheduleCard;

import java.util.List;

@Mapper
public interface ClassScheduleCardMysqlMapper extends ClassScheduleCardMapper {

    @InsertProvider(type = ClassScheduleCardProvider.class, method = "insert")
    int insert(@Param("record") ClassScheduleCard record);

    @SelectProvider(type = ClassScheduleCardProvider.class, method = "selectByIdList")
    List<ClassScheduleCard> selectByIdList(@Param("idList") List<Object> idList);

    @DeleteProvider(type = ClassScheduleCardProvider.class, method = "deleteByIdList")
    Integer deleteByIdList(@Param("idList") List<Object> idList);

    @DeleteProvider(type = ClassScheduleCardProvider.class, method = "deleteByTeacherCourseIdList")
    Integer deleteByTeacherCourseIdList(@Param("idList") List<Object> idList);
}