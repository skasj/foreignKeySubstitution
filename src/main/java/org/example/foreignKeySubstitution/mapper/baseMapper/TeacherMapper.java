package org.example.foreignKeySubstitution.mapper.baseMapper;

import org.example.foreignKeySubstitution.modal.entity.Teacher;

public interface TeacherMapper {
    int insert(Teacher record, String tableName);

    Teacher selectByPrimaryKey(Integer id);
}