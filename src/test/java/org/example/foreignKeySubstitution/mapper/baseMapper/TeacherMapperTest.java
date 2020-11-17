package org.example.foreignKeySubstitution.mapper.baseMapper;

import org.example.foreignKeySubstitution.mapper.MapperBaseTest;
import org.example.foreignKeySubstitution.modal.entity.Teacher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

public class TeacherMapperTest extends MapperBaseTest {

    private Teacher record1;
    private Teacher record2;

    @Resource
    private TeacherMapper mapper;

    @Before
    public void init(){
        record1 = new Teacher(3,"ydy","123456");
        record1 = new Teacher(4,"yyd","123457");
    }

    @Test
    public void selectByPrimaryKey() {
        mapper.insert(record1);
        Teacher fromDB = mapper.selectByPrimaryKey(record1.getId());
        Assert.assertEquals(record1,fromDB);
    }
}