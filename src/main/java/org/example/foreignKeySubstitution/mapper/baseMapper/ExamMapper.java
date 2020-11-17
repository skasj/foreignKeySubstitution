package org.example.foreignKeySubstitution.mapper.baseMapper;

import org.example.foreignKeySubstitution.modal.entity.Exam;

public interface ExamMapper {
    int insert(Exam record);

    Exam findById(String id);
}
