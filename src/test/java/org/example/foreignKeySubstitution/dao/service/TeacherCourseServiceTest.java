package org.example.foreignKeySubstitution.dao.service;

import org.example.foreignKeySubstitution.mapper.MapperBaseTest;
import org.example.foreignKeySubstitution.mapper.baseMapper.CourseMapper;
import org.example.foreignKeySubstitution.mapper.baseMapper.TeacherCourseMapper;
import org.example.foreignKeySubstitution.mapper.baseMapper.TeacherMapper;
import org.example.foreignKeySubstitution.modal.entity.Teacher;
import org.example.foreignKeySubstitution.modal.entity.TeacherCourse;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;

public class TeacherCourseServiceTest extends MapperBaseTest {

    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private TeacherCourseMapper teacherCourseMapper;

    @Before
    public void setUp() {
    }

    @Test
    public void selectByIdList() {

    }
}