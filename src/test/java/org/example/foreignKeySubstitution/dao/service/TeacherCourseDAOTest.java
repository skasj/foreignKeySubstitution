package org.example.foreignKeySubstitution.dao.service;

import org.example.foreignKeySubstitution.mapper.baseMapper.TeacherCourseMapper;
import org.example.foreignKeySubstitution.mapper.baseMapper.TeacherMapper;
import org.example.foreignKeySubstitution.modal.dto.TeacherCourseDTO;
import org.example.foreignKeySubstitution.modal.entity.Teacher;
import org.example.foreignKeySubstitution.modal.entity.TeacherCourse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

public class TeacherCourseDAOTest extends DAOBaseTest {

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
        List<TeacherCourseDTO> fromDB = teacherCourseDAO.selectByIdList(
                Arrays.asList(teacherCourse1.getId(), teacherCourse2.getId()));
        Assert.assertEquals(2, fromDB.size());
        Assert.assertTrue(fromDB.stream().anyMatch(o->o.getTeacherName().equals(teacher1.getName())));
        Assert.assertTrue(fromDB.stream().anyMatch(o->o.getTeacherName().equals(teacher2.getName())));
    }
}