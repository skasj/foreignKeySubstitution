package org.example.foreignKeySubstitution.mapper.baseMapper;

import org.example.foreignKeySubstitution.modal.entity.Course;

public interface CourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Course record,String userName);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);
}