package org.example.foreignKeySubstitution.mapper.baseMapper;

import org.example.foreignKeySubstitution.modal.entity.Study;

public interface StudyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Study record);

    int insertSelective(Study record);

    Study selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Study record);

    int updateByPrimaryKey(Study record);
}