package org.example.foreignKeySubstitution.mapper.baseProvider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.example.foreignKeySubstitution.mapper.providerUtils.DeleteSQLAssembler;
import org.example.foreignKeySubstitution.mapper.providerUtils.InsertSQLAssembler;
import org.example.foreignKeySubstitution.mapper.providerUtils.SelectSQLAssembler;
import org.example.foreignKeySubstitution.modal.entity.ClassScheduleCard;

import java.util.List;

public class ClassScheduleCardProvider implements ProviderMethodResolver {

    private final String tableName = "class_schedule_card";
    private final Class<?> javaType = ClassScheduleCard.class;

    public String insert(@Param("record") ClassScheduleCard record) {
        return InsertSQLAssembler.insertRecord(record, tableName);
    }

    public String selectByIdList(@Param("idList") List<Object> idList) {
        return SelectSQLAssembler.selectByIdList(idList, tableName, javaType);
    }

    public String deleteByIdList(@Param("idList") List<Object> idList) {
        return DeleteSQLAssembler.deleteByIdList(idList, tableName);
    }
}
