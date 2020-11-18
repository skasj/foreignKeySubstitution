package org.example.foreignKeySubstitution.mapper.mysqlMapper;

import org.example.foreignKeySubstitution.mapper.MapperBaseTest;
import org.example.foreignKeySubstitution.mapper.baseMapper.TeacherMapper;
import org.example.foreignKeySubstitution.modal.entity.Teacher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

public class TeacherMapperTest extends MapperBaseTest {

    private Teacher record1;
    private Teacher record2;

    @Resource
    private TeacherMapper mapper;

    @Before
    public void init(){
        record1 = new Teacher(3, "ydy", "123456");
        record2 = new Teacher(4, "yyd", "123457");
    }

    @Test
    public void selectByPrimaryKey() {
        mapper.insert(record1);
        Teacher fromDB = mapper.selectByPrimaryKey(record1.getId());
        Assert.assertEquals(record1, fromDB);
    }

    @Test
    public void selectByIdList() {
        mapper.insert(record1);
        mapper.insert(record2);
        List<Teacher> teacherList = mapper.selectByIdList(Arrays.asList(record1.getId(), record2.getId()));
        Assert.assertEquals(2, teacherList.size());
    }

    @Test
    public void deleteByIdList() {
        mapper.insert(record1);
        mapper.insert(record2);
        List<Teacher> teacherList = mapper.selectByIdList(Arrays.asList(record1.getId(), record2.getId()));
        Assert.assertEquals(2, teacherList.size());
        mapper.deleteByIdList(Arrays.asList(record1.getId(), record2.getId()));
        teacherList = mapper.selectByIdList(Arrays.asList(record1.getId(), record2.getId()));
        Assert.assertEquals(0, teacherList.size());
    }
}