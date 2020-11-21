package org.example.foreignKeySubstitution.mapper.mysqlMapper;

import org.example.foreignKeySubstitution.mapper.MapperBaseTest;
import org.example.foreignKeySubstitution.mapper.baseMapper.TeacherMapper;
import org.example.foreignKeySubstitution.modal.entity.Teacher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TeacherMysqlMapperTest extends MapperBaseTest {

    private Teacher record1;
    private Teacher record2;

    @Resource
    private TeacherMapper mapper;

    @Before
    public void init() {
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

    @Test
    public void batchUpdateById() {
        mapper.insert(record1);
        mapper.insert(record2);
        List<Teacher> teacherList = mapper.selectByIdList(Arrays.asList(record1.getId(), record2.getId()));
        Assert.assertEquals(2, teacherList.size());
        int tmp = record2.getId();
        record2.setId(record1.getId());
        record1.setId(tmp);
        mapper.batchUpdateById(Arrays.asList(record1,record2));
        Teacher teacher1 = mapper.selectByPrimaryKey(record1.getId());
        Assert.assertEquals(teacher1.getName(), record1.getName());
        Teacher teacher2 = mapper.selectByPrimaryKey(record2.getId());
        Assert.assertEquals(teacher2.getName(), record2.getName());
    }
}