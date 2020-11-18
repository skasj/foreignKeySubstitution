package org.example.foreignKeySubstitution.dao.service;

import org.example.foreignKeySubstitution.mapper.baseMapper.TeacherMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TeacherDAOImpl implements TeacherDAO {

    @Resource
    private TeacherMapper teacherMapper;

    // 级联删除
    @Override
    public int deleteByIdList(List<Object> idList) {
        if (CollectionUtils.isEmpty(idList)){
            return 0;
        }
        return teacherMapper.deleteByIdList(idList);
    }
}
