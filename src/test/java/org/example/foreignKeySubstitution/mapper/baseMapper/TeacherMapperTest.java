package org.example.foreignKeySubstitution.mapper.baseMapper;

import org.example.foreignKeySubstitution.dao.service.DAOBaseTest;
import org.example.foreignKeySubstitution.modal.entity.ClassScheduleCard;
import org.example.foreignKeySubstitution.modal.entity.Teacher;
import org.example.foreignKeySubstitution.modal.entity.TeacherCourse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TeacherMapperTest extends DAOBaseTest {

    private Teacher teacherRecord1;
    private Teacher teacherRecord2;
    private TeacherCourse teacherCourse1;
    private TeacherCourse teacherCourse2;
    private ClassScheduleCard classScheduleCard1;
    private ClassScheduleCard classScheduleCard2;

    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private TeacherCourseMapper teacherCourseMapper;
    @Resource
    private ClassScheduleCardMapper classScheduleCardMapper;
    @Before
    public void init() {
        teacherRecord1 = new Teacher(3, "ydy", "123456");
        teacherRecord2 = new Teacher(4, "yyd", "123457");
        teacherCourse1 = new TeacherCourse(5, 3, 1);
        teacherCourse2 = new TeacherCourse(6, 4, 2);
        classScheduleCard1 = new ClassScheduleCard(1, new Date(), 5, 0, -1);
        classScheduleCard2 = new ClassScheduleCard(2, new Date(), 6, 0, -1);
    }

    @Test
    public void selectByPrimaryKey() {
        teacherMapper.insert(teacherRecord1);
        Teacher fromDB = teacherMapper.selectByPrimaryKey(teacherRecord1.getId());
        Assert.assertEquals(teacherRecord1, fromDB);
    }

    @Test
    public void selectByIdList() {
        teacherMapper.insert(teacherRecord1);
        teacherMapper.insert(teacherRecord2);
        List<Teacher> teacherList = teacherMapper.selectByIdList(Arrays.asList(teacherRecord1.getId(), teacherRecord2.getId()));
        Assert.assertEquals(2, teacherList.size());
    }

    @Test
    public void batchUpdateById() {
        teacherMapper.insert(teacherRecord1);
        teacherMapper.insert(teacherRecord2);
        List<Teacher> teacherList = teacherMapper.selectByIdList(Arrays.asList(teacherRecord1.getId(), teacherRecord2.getId()));
        Assert.assertEquals(2, teacherList.size());
        int tmp = teacherRecord2.getId();
        teacherRecord2.setId(teacherRecord1.getId());
        teacherRecord1.setId(tmp);
        teacherMapper.batchUpdateById(Arrays.asList(teacherRecord1, teacherRecord2));
        Teacher fromDB1 = teacherMapper.selectByPrimaryKey(teacherRecord1.getId());
        Assert.assertEquals(fromDB1.getName(), teacherRecord1.getName());
        Teacher fromDB2 = teacherMapper.selectByPrimaryKey(teacherRecord2.getId());
        Assert.assertEquals(fromDB2.getName(), teacherRecord2.getName());
    }

    @Test
    public void deleteByIdList() {
        teacherMapper.insert(teacherRecord1);
        teacherMapper.insert(teacherRecord2);
        teacherCourseMapper.insert(teacherCourse1);
        teacherCourseMapper.insert(teacherCourse2);
        classScheduleCardMapper.insert(classScheduleCard1);
        classScheduleCardMapper.insert(classScheduleCard2);
        Assert.assertEquals(2,
                teacherCourseMapper.selectByIdList(Arrays.asList(teacherCourse1.getId(), teacherCourse2.getId())).size());
        Assert.assertEquals(2,
                classScheduleCardMapper.selectByIdList(Arrays.asList(classScheduleCard1.getId(), classScheduleCard2.getId())).size());
        teacherMapper.deleteByIdList(Arrays.asList(teacherRecord1.getId(), teacherRecord2.getId()));
        Assert.assertEquals(0,
                teacherCourseMapper.selectByIdList(Arrays.asList(teacherCourse1.getId(), teacherCourse2.getId()))
                        .size());
        Assert.assertEquals(0,
                classScheduleCardMapper.selectByIdList(Arrays.asList(classScheduleCard1.getId(), classScheduleCard2.getId())).size());
    }

    @Test
    public void deleteByNameAndTelephone() {
        teacherMapper.insert(teacherRecord1);
        teacherMapper.insert(teacherRecord2);
        teacherCourseMapper.insert(teacherCourse1);
        teacherCourseMapper.insert(teacherCourse2);
        List<Integer> idList1 = teacherMapper.selectIdListByNameAndTelephone(teacherRecord1.getName(),
                teacherRecord1.getTelephone());
        Assert.assertEquals(1, idList1.size());
        List<TeacherCourse> teacherCourseList1 = teacherCourseMapper.selectByIdList(
                Collections.singletonList(teacherCourse1.getId()));
        Assert.assertEquals(1, teacherCourseList1.size());
        teacherMapper.deleteByNameAndTelephone(teacherRecord1.getName(), teacherRecord1.getTelephone());
        List<Integer> idList2 = teacherMapper.selectIdListByNameAndTelephone(teacherRecord1.getName(),
                teacherRecord1.getTelephone());
        Assert.assertEquals(0, idList2.size());
        List<TeacherCourse> teacherCourseList2 = teacherCourseMapper.selectByIdList(
                Collections.singletonList(teacherCourse1.getId()));
        Assert.assertEquals(0, teacherCourseList2.size());
    }
}