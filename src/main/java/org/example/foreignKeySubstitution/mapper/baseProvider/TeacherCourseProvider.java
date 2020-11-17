package org.example.foreignKeySubstitution.mapper.baseProvider;

import org.apache.ibatis.annotations.Param;
import org.example.foreignKeySubstitution.mapper.providerUtils.DeleteSQLAssembler;
import org.example.foreignKeySubstitution.mapper.providerUtils.InsertSQLAssembler;
import org.example.foreignKeySubstitution.mapper.providerUtils.SelectSQLAssembler;
import org.example.foreignKeySubstitution.modal.entity.TeacherCourse;

import java.util.List;

public class TeacherCourseProvider {

    private final String tableName = "teacher_course";
    private final Class<?> javaType = TeacherCourse.class;

    public String insert(@Param("record") TeacherCourse record) {
        return InsertSQLAssembler.insertRecord(record, tableName);
    }

    public String selectByIdList(@Param("idList") List<Object> idList) {
        return SelectSQLAssembler.selectByIdList(idList, tableName, javaType);
    }

    public String deleteByIdList(@Param("idList") List<Object> idList) {
        return DeleteSQLAssembler.deleteByIdList(idList, tableName);
    }
}
