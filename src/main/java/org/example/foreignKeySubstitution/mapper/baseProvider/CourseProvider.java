package org.example.foreignKeySubstitution.mapper.baseProvider;

import com.google.common.base.Joiner;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;
import org.example.foreignKeySubstitution.mapper.providerUtils.InsertSQLAssembler;
import org.example.foreignKeySubstitution.modal.entity.Course;

import java.util.List;

public class CourseProvider implements ProviderMethodResolver {

    String tableName = "course";
    Joiner joiner = Joiner.on("','");

    public String insert(@Param("record") Course record) {
        return InsertSQLAssembler.insertRecord(record, tableName);
    }


    public String selectByIdList(@Param("idList") List<Integer> idList) {
        return new SQL().SELECT("id", "name")
                .FROM(tableName)
                .WHERE("id in ('" + joiner.join(idList) + "')")
                .toString();
    }

    public String deleteByIdList(@Param("idList") List<Integer> idList) {
        return new SQL().DELETE_FROM(tableName)
                .WHERE("id in ('" + joiner.join(idList) + "')")
                .toString();
    }

}
