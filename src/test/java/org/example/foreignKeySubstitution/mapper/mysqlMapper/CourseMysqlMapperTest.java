package org.example.foreignKeySubstitution.mapper.mysqlMapper;

import org.example.foreignKeySubstitution.mapper.MapperBaseTest;
import org.example.foreignKeySubstitution.mapper.baseMapper.CourseMapper;
import org.example.foreignKeySubstitution.modal.entity.Course;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

public class CourseMysqlMapperTest extends MapperBaseTest {

    private Course record1;
    private Course record2;

    @Resource
    private CourseMapper mapper;

    @Before
    public void init(){
        record1 = new Course(3,"数学");
        record2 = new Course(4,"语文");
    }

    @Test
    public void insert() {

    }

    @Test
    public void selectByPrimaryKey() {
        mapper.insert(record1, "course");
        Object fromDB = mapper.selectByPrimaryKey(record1.getId());
        Assert.assertEquals(record1, fromDB);
    }

    @Test
    public void selectByIdList() {
        mapper.insert(record1, "course");
        mapper.insert(record2, "course");
        List<Course> courseList = mapper.selectByIdList(Arrays.asList(record1.getId(), record2.getId()));
        Assert.assertEquals(2, courseList.size());
    }

    @Test
    public void deleteByIdList() {
        mapper.insert(record1, "course");
        mapper.insert(record2, "course");
        List<Course> courseList = mapper.selectByIdList(Arrays.asList(record1.getId(), record2.getId()));
        Assert.assertEquals(2, courseList.size());
        mapper.deleteByIdList(Arrays.asList(record1.getId(), record2.getId()));
        courseList = mapper.selectByIdList(Arrays.asList(record1.getId(), record2.getId()));
        Assert.assertEquals(0, courseList.size());
    }
}