package org.example.foreignKeySubstitution.mapper.baseProvider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.example.foreignKeySubstitution.mapper.providerUtils.InsertSQLAssembler;
import org.example.foreignKeySubstitution.mapper.providerUtils.SelectSQLAssembler;
import org.example.foreignKeySubstitution.modal.entity.Teacher;

import java.util.List;

public class TeacherProvider implements ProviderMethodResolver {

    private String tableName = "teacher";
    private InsertSQLAssembler assembler = new InsertSQLAssembler();

    public String insert(@Param("record") Teacher record) {
        return assembler.insertRecord(record, tableName);
    }

    public String selectByIdList(@Param("idList") List<Object> idList) {
        return SelectSQLAssembler.selectByIdList(idList, tableName, Teacher.class);
    }
}
