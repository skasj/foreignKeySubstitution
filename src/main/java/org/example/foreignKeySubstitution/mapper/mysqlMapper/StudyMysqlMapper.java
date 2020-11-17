package org.example.foreignKeySubstitution.mapper.mysqlMapper;

import org.example.foreignKeySubstitution.mapper.baseMapper.StudyMapper;
import org.example.foreignKeySubstitution.modal.entity.Study;

public interface StudyMysqlMapper extends StudyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Study record);

    int insertSelective(Study record);

    Study selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Study record);

    int updateByPrimaryKey(Study record);
}