package org.example.foreignKeySubstitution.mapper.baseProvider;

import com.google.common.base.Joiner;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.example.foreignKeySubstitution.mapper.providerUtils.DeleteSQLAssembler;
import org.example.foreignKeySubstitution.mapper.providerUtils.InsertSQLAssembler;
import org.example.foreignKeySubstitution.mapper.providerUtils.SelectSQLAssembler;
import org.example.foreignKeySubstitution.modal.entity.TeacherCourse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TeacherCourseProvider {

    private static final String tableName = "teacher_course";
    private static final Class<?> javaType = TeacherCourse.class;

    public String insert(@Param("record") TeacherCourse record) {
        return InsertSQLAssembler.insertRecord(record, tableName);
    }

    public String selectByIdList(@Param("idList") List<Object> idList) {
        return SelectSQLAssembler.selectByIdList(idList, tableName, javaType);
    }

    public String deleteByIdList(@Param("idList") List<Object> idList) {
        return DeleteSQLAssembler.deleteByIdList(idList, tableName);
    }

    public String selectIdListByTeacherIdList(@Param("idList") List<Object> idList) {
        Joiner joiner = Joiner.on(",");
        List<String> idListString = new ArrayList<>();
        IntStream.range(0, idList.size())
                .forEach(id -> idListString.add(String.format("#{idList[%d]}", id)));
        return new SQL().SELECT("id")
                .FROM(tableName)
                .WHERE("teacher_id in (" + joiner.join(idListString) + ")")
                .toString();
    }

    public String deleteByTeacherIdList(@Param("idList") List<Object> idList) {
        Joiner joiner = Joiner.on(",");
        List<String> idListString = new ArrayList<>();
        IntStream.range(0, idList.size())
                .forEach(id -> idListString.add(String.format("#{idList[%d]}", id)));
        return new SQL().DELETE_FROM(tableName)
                .WHERE("teacher_id in (" + joiner.join(idListString) + ")")
                .toString();
    }
}
