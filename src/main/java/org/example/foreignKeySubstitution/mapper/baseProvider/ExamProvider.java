package org.example.foreignKeySubstitution.mapper.baseProvider;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class ExamProvider implements ProviderMethodResolver {
    String tableName = "exam";

    public String insert(){
        return new SQL().INSERT_INTO(tableName)
                .VALUES("id","#{record.id}")
                .VALUES("context","#{record.context, jdbcType=VARCHAR, typeHandler=org.example.foreignKeySubstitution.mapper.handles.JsonTypeHandler}").toString();
    }

    public String findById(){
        return new SQL().SELECT("id","context").FROM(tableName).WHERE("id = #{id}").toString();
    }
}
