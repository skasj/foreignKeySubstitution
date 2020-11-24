package org.example.foreignKeySubstitution.mapper.baseMapper;

import org.example.foreignKeySubstitution.mapper.MapperBaseTest;
import org.example.foreignKeySubstitution.modal.entity.ClassScheduleCard;
import org.example.foreignKeySubstitution.modal.entity.Course;
import org.example.foreignKeySubstitution.modal.entity.Teacher;
import org.example.foreignKeySubstitution.modal.entity.TeacherCourse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TeacherCourseMapperTest extends MapperBaseTest {

    private TeacherCourse teacherCourse1;
    private TeacherCourse teacherCourse2;
    private Teacher teacherRecord1;
    private Teacher teacherRecord2;
    private Course courseRecord1;
    private Course courseRecord2;

    @Resource
    private TeacherCourseMapper mapper;

    @Before
    public void init() {
        teacherCourse1 = new TeacherCourse(3, 1, 1);
        teacherCourse2 = new TeacherCourse(4, 2, 2);
        teacherRecord1 = new Teacher(3, "ydy", "123456");
        teacherRecord2 = new Teacher(4, "yyd", "123457");
        courseRecord1 = new Course(3,"数学");
        courseRecord2 = new Course(4,"语文");
    }

    @Test
    public void selectByPrimaryKey() {
        mapper.insert(teacherCourse1);
        TeacherCourse fromDB = mapper.selectByPrimaryKey(teacherCourse1.getId());
        Assert.assertEquals(teacherCourse1, fromDB);
    }

    @Test
    public void selectByIdList() {
        mapper.insert(teacherCourse1);
        mapper.insert(teacherCourse2);
        List<TeacherCourse> fromDBList = mapper.selectByIdList(Arrays.asList(teacherCourse1.getId(), teacherCourse2.getId()));
        Assert.assertEquals(2, fromDBList.size());
    }

    @Test
    public void deleteByIdList() {
        mapper.insert(teacherCourse1);
        mapper.insert(teacherCourse2);
        List<TeacherCourse> fromDBList1 = mapper.selectByIdList(Arrays.asList(teacherCourse1.getId(), teacherCourse2.getId()));
        Assert.assertEquals(2, fromDBList1.size());
        mapper.deleteByIdList(Arrays.asList(teacherCourse1.getId(), teacherCourse2.getId()));
        List<TeacherCourse> fromDBList2 = mapper.selectByIdList(Arrays.asList(teacherCourse1.getId(), teacherCourse2.getId()));
        Assert.assertEquals(0, fromDBList2.size());
    }

    @Test
    public void insertList() {
        Assert.assertEquals(2, (int) mapper.insertList(Arrays.asList(teacherCourse1, teacherCourse2)));
        Assert.assertEquals(2, mapper.selectByIdList(Arrays.asList(teacherCourse1.getId(), teacherCourse2.getId())).size());
    }
}