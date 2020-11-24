package org.example.foreignKeySubstitution.mapper.baseMapper;

import org.example.foreignKeySubstitution.modal.entity.Course;

import java.util.List;

public interface CourseMapper {
    int deleteByIdList(List<Integer> idList);

    int insert(Course record);

    Course selectByPrimaryKey(Integer id);

    List<Course> selectByIdList(List<Integer> idList);

    Integer countByIdList(List<Object> idList);
}