package org.example.foreignKeySubstitution.mapper.baseMapper;

import java.util.List;

public interface BaseMapper<E> {
    int insert(E record);

    E selectByPrimaryKey(Integer id);

    List<E> selectByIdList(List<Object> idList);

    Integer deleteByIdList(List<Object> idList);
}
