package org.example.foreignKeySubstitution.mapper.baseMapper;

import org.example.foreignKeySubstitution.mapper.MapperBaseTest;
import org.example.foreignKeySubstitution.mapper.baseMapper.TeacherCourseMapper;
import org.example.foreignKeySubstitution.modal.entity.TeacherCourse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

public class TeacherCourseMapperTest extends MapperBaseTest {

    private TeacherCourse record1;
    private TeacherCourse record2;

    @Resource
    private TeacherCourseMapper mapper;

    @Before
    public void init() {
        record1 = new TeacherCourse(3, 1, 1);
        record2 = new TeacherCourse(4, 2, 2);
    }

    @Test
    public void selectByPrimaryKey() {
        mapper.insert(record1);
        TeacherCourse fromDB = mapper.selectByPrimaryKey(record1.getId());
        Assert.assertEquals(record1, fromDB);
    }

    @Test
    public void selectByIdList() {
        mapper.insert(record1);
        mapper.insert(record2);
        List<TeacherCourse> fromDBList = mapper.selectByIdList(Arrays.asList(record1.getId(), record2.getId()));
        Assert.assertEquals(2, fromDBList.size());
    }

    @Test
    public void deleteByIdList() {
        mapper.insert(record1);
        mapper.insert(record2);
        List<TeacherCourse> fromDBList1 = mapper.selectByIdList(Arrays.asList(record1.getId(), record2.getId()));
        Assert.assertEquals(2, fromDBList1.size());
        mapper.deleteByIdList(Arrays.asList(record1.getId(), record2.getId()));
        List<TeacherCourse> fromDBList2 = mapper.selectByIdList(Arrays.asList(record1.getId(), record2.getId()));
        Assert.assertEquals(0, fromDBList2.size());
    }
}