package org.example.foreignKeySubstitution.mapper.baseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.foreignKeySubstitution.modal.entity.Course;

@Mapper
public interface CourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);
}