package org.example.foreignKeySubstitution.mapper.baseProvider;

import com.google.common.base.Joiner;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;
import org.example.foreignKeySubstitution.mapper.providerUtils.DeleteSQLAssembler;
import org.example.foreignKeySubstitution.mapper.providerUtils.InsertSQLAssembler;
import org.example.foreignKeySubstitution.mapper.providerUtils.SelectSQLAssembler;
import org.example.foreignKeySubstitution.modal.entity.ClassScheduleCard;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ClassScheduleCardProvider implements ProviderMethodResolver {

    private static final String tableName = "class_schedule_card";
    private static final Class<?> javaType = ClassScheduleCard.class;

    public String insert(@Param("record") ClassScheduleCard record) {
        return InsertSQLAssembler.insertRecord(record, tableName);
    }

    public String selectByIdList(@Param("idList") List<Object> idList) {
        return SelectSQLAssembler.selectByIdList(idList, tableName, javaType);
    }

    public String deleteByIdList(@Param("idList") List<Object> idList) {
        return DeleteSQLAssembler.deleteByIdList(idList, tableName);
    }

    public String deleteByTeacherCourseIdList(@Param("idList") List<Object> idList) {
        List<String> idListString = new ArrayList<>();
        IntStream.range(0, idList.size())
                .forEach(id -> idListString.add(String.format("#{idList[%d]}", id)));
        return new SQL().DELETE_FROM(tableName)
                .WHERE("teacher_course_id in (" + Joiner.on(',')
                        .join(idListString) + ")")
                .toString();
    }
}
