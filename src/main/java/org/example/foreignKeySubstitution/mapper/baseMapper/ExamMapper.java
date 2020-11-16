package org.example.foreignKeySubstitution.mapper.baseMapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.example.foreignKeySubstitution.mapper.baseProvider.ExamProvider;
import org.example.foreignKeySubstitution.mapper.handles.JsonListHandler;
import org.example.foreignKeySubstitution.modal.entity.Exam;
import org.example.foreignKeySubstitution.modal.entity.ExamRequest;

@Mapper
public interface ExamMapper {
    @InsertProvider(type = ExamProvider.class)
    int insert(@Param("record") Exam record);

    @Results({
            @Result(property = "id", column = "id", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "context", column = "context", javaType = ExamRequest.class, typeHandler = JsonListHandler.class, jdbcType = JdbcType.VARCHAR)
    })
    @SelectProvider(type = ExamProvider.class)
    Exam findById(@Param("id") String id);
}
