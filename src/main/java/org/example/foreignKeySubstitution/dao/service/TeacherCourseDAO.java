package org.example.foreignKeySubstitution.dao.service;

import org.example.foreignKeySubstitution.modal.dto.TeacherCourseDTO;

import java.util.List;

public interface TeacherCourseDAO {

    /**
     * 1查询条件集中在一个表上的情况, 其他字段只需要填充
     */
    List<TeacherCourseDTO> selectByIdList(List<Object> idList);

    /**
     * 批量删除
     */
    Integer deleteByIdList(List<Object> idList);
}
