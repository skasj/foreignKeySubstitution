package org.example.foreignKeySubstitution.dao.service;

import org.example.foreignKeySubstitution.FKSApplication;
import org.example.foreignKeySubstitution.mapper.MapperBaseTest;
import org.example.foreignKeySubstitution.mapper.baseMapper.TeacherCourseMapper;
import org.example.foreignKeySubstitution.mapper.baseMapper.TeacherMapper;
import org.example.foreignKeySubstitution.modal.entity.Teacher;
import org.example.foreignKeySubstitution.modal.entity.TeacherCourse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FKSApplication.class)
@Transactional
public class TeacherCourseDAOTest {

    private Teacher teacher1;
    private Teacher teacher2;
    private TeacherCourse teacherCourse1;
    private TeacherCourse teacherCourse2;

    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private TeacherCourseMapper teacherCourseMapper;
    @Resource
    private TeacherCourseDAO teacherCourseDAO;

    @Before
    public void init() {
        teacher1 = new Teacher(10, "李华", "1234567");
        teacher2 = new Teacher(11, "王刚", "1134567");
        teacherCourse1 = new TeacherCourse(5, 10, 1);
        teacherCourse2 = new TeacherCourse(6, 11, 2);
    }

    @Test
    public void selectByIdList() {
        teacherMapper.insert(teacher1);
        teacherMapper.insert(teacher2);
        teacherCourseMapper.insert(teacherCourse1);
        teacherCourseMapper.insert(teacherCourse2);
        Assert.assertEquals(2, teacherCourseDAO.selectByIdList(Arrays.asList(teacherCourse1.getId(), teacherCourse2.getId()))
                .size());
    }
}