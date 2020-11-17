package org.example.foreignKeySubstitution.mapper.mysqlMapper;

import org.example.foreignKeySubstitution.mapper.MapperBaseTest;
import org.example.foreignKeySubstitution.mapper.baseMapper.CourseMapper;
import org.example.foreignKeySubstitution.modal.entity.Course;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;

public class CourseMysqlMapperTest extends MapperBaseTest {

    private Course record1;
    private Course record2;

    @Resource
    private CourseMapper mapper;

    @Before
    public void init(){
        record1 = new Course(3,"数学");
        record1 = new Course(4,"语文");
    }

    @Test
    public void insert() {

    }

    @Test
    public void selectByPrimaryKey() {
        mapper.insert(record1,"course");
        Object fromDB = mapper.selectByPrimaryKey(record1.getId());
        Assert.assertEquals(record1,fromDB);
    }
}