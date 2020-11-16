package org.example.foreignKeySubstitution.mapper.baseMapper;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.example.foreignKeySubstitution.mapper.baseProvider.ExamProvider;
import org.example.foreignKeySubstitution.modal.entity.Exam;

@Mapper
public interface ExamMapper {
    @InsertProvider(type = ExamProvider.class)
    int insert(@Param("record") Exam record);

    @SelectProvider(type = ExamProvider.class)
    Exam findById(@Param("id") String id);
}
