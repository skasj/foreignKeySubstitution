package org.example.foreignKeySubstitution.mapper.baseMapper;

import org.example.foreignKeySubstitution.annotation.CascadingDelete;
import org.example.foreignKeySubstitution.annotation.CascadingDeleteList;
import org.example.foreignKeySubstitution.modal.entity.Teacher;

import java.util.List;

public interface TeacherMapper extends BaseMapper<Teacher> {

    int insert(Teacher record);

    Teacher selectByPrimaryKey(Integer id);

    List<Teacher> selectByIdList(List<Object> idList);

    Integer deleteByIdList(List<Object> idList);

    Integer batchUpdateById(List<Teacher> teacherList);

}