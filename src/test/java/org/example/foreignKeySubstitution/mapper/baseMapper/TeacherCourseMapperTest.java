package org.example.foreignKeySubstitution.mapper.baseMapper;

import org.example.foreignKeySubstitution.dao.service.DAOBaseTest;
import org.example.foreignKeySubstitution.exception.ForeignKeyNoExistException;
import org.example.foreignKeySubstitution.modal.entity.Course;
import org.example.foreignKeySubstitution.modal.entity.Teacher;
import org.example.foreignKeySubstitution.modal.entity.TeacherCourse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

public class TeacherCourseMapperTest extends DAOBaseTest {

    private TeacherCourse teacherCourse1;
    private TeacherCourse teacherCourse2;
    private Teacher teacherRecord1;
    private Teacher teacherRecord2;
    private Course courseRecord1;
    private Course courseRecord2;

    @Resource
    private TeacherCourseMapper teacherCourseMapper;
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private TeacherMapper teacherMapper;


    @Before
    public void init() {
        teacherCourse1 = new TeacherCourse(3, 3, 3);
        teacherCourse2 = new TeacherCourse(4, 4, 4);
        teacherRecord1 = new Teacher(3, "ydy", "123456");
        teacherRecord2 = new Teacher(4, "yyd", "123457");
        courseRecord1 = new Course(3,"数学");
        courseRecord2 = new Course(4,"语文");
    }

    @Test
    public void selectByPrimaryKey() {
        teacherCourseMapper.insert(teacherCourse1);
        TeacherCourse fromDB = teacherCourseMapper.selectByPrimaryKey(teacherCourse1.getId());
        Assert.assertEquals(teacherCourse1, fromDB);
    }

    @Test
    public void selectByIdList() {
        teacherCourseMapper.insert(teacherCourse1);
        teacherCourseMapper.insert(teacherCourse2);
        List<TeacherCourse> fromDBList = teacherCourseMapper.selectByIdList(Arrays.asList(teacherCourse1.getId(), teacherCourse2.getId()));
        Assert.assertEquals(2, fromDBList.size());
    }

    @Test
    public void deleteByIdList() {
        teacherCourseMapper.insert(teacherCourse1);
        teacherCourseMapper.insert(teacherCourse2);
        List<TeacherCourse> fromDBList1 = teacherCourseMapper.selectByIdList(Arrays.asList(teacherCourse1.getId(), teacherCourse2.getId()));
        Assert.assertEquals(2, fromDBList1.size());
        teacherCourseMapper.deleteByIdList(Arrays.asList(teacherCourse1.getId(), teacherCourse2.getId()));
        List<TeacherCourse> fromDBList2 = teacherCourseMapper.selectByIdList(Arrays.asList(teacherCourse1.getId(), teacherCourse2.getId()));
        Assert.assertEquals(0, fromDBList2.size());
    }

    @Test
    public void insertList() {
        try{
            teacherCourseMapper.insertList(Arrays.asList(teacherCourse1, teacherCourse2));
        } catch (Exception e){
            Assert.assertTrue(e instanceof ForeignKeyNoExistException);
        }
        courseMapper.insert(courseRecord1);
        courseMapper.insert(courseRecord2);
        teacherMapper.insert(teacherRecord1);
        teacherMapper.insert(teacherRecord2);
        Assert.assertEquals(2,(int) teacherCourseMapper.insertList(Arrays.asList(teacherCourse1, teacherCourse2)));
        Assert.assertEquals(2, teacherCourseMapper.selectByIdList(Arrays.asList(teacherCourse1.getId(), teacherCourse2.getId())).size());
    }
}