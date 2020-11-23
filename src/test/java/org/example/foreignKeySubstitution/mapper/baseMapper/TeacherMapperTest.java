package org.example.foreignKeySubstitution.mapper.baseMapper;

import org.example.foreignKeySubstitution.dao.service.DAOBaseTest;
import org.example.foreignKeySubstitution.mapper.MapperBaseTest;
import org.example.foreignKeySubstitution.mapper.baseMapper.TeacherCourseMapper;
import org.example.foreignKeySubstitution.mapper.baseMapper.TeacherMapper;
import org.example.foreignKeySubstitution.modal.entity.Teacher;
import org.example.foreignKeySubstitution.modal.entity.TeacherCourse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

public class TeacherMapperTest extends DAOBaseTest {

    private Teacher record1;
    private Teacher record2;
    private TeacherCourse teacherCourse1;
    private TeacherCourse teacherCourse2;

    @Resource
    private TeacherMapper mapper;
    @Resource
    private TeacherCourseMapper teacherCourseMapper;

    @Before
    public void init() {
        record1 = new Teacher(3, "ydy", "123456");
        record2 = new Teacher(4, "yyd", "123457");
        teacherCourse1 = new TeacherCourse(3, 10, 1);
        teacherCourse2 = new TeacherCourse(4, 11, 2);
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
    public void batchUpdateById() {
        mapper.insert(record1);
        mapper.insert(record2);
        List<Teacher> teacherList = mapper.selectByIdList(Arrays.asList(record1.getId(), record2.getId()));
        Assert.assertEquals(2, teacherList.size());
        int tmp = record2.getId();
        record2.setId(record1.getId());
        record1.setId(tmp);
        mapper.batchUpdateById(Arrays.asList(record1, record2));
        Teacher fromDB1 = mapper.selectByPrimaryKey(record1.getId());
        Assert.assertEquals(fromDB1.getName(), record1.getName());
        Teacher fromDB2 = mapper.selectByPrimaryKey(record2.getId());
        Assert.assertEquals(fromDB2.getName(), record2.getName());
    }

    @Test
    public void deleteByIdList() {
        mapper.insert(record1);
        mapper.insert(record2);
        teacherCourseMapper.insert(teacherCourse1);
        teacherCourseMapper.insert(teacherCourse2);
        Assert.assertEquals(2,
                teacherCourseMapper.selectByIdList(Arrays.asList(teacherCourse1.getId(), teacherCourse2.getId()))
                        .size());
        mapper.deleteByIdList(Arrays.asList(record1.getId(), record2.getId()));
        Assert.assertEquals(0, teacherCourseMapper.selectByIdList(Arrays.asList(teacherCourse1.getId(), teacherCourse2.getId()))
                .size());
    }
}