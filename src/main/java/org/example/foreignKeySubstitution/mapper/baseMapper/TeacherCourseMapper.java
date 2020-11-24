package org.example.foreignKeySubstitution.mapper.baseMapper;

import org.example.foreignKeySubstitution.annotation.*;
import org.example.foreignKeySubstitution.modal.entity.TeacherCourse;

import java.util.List;

@Cascading
public interface TeacherCourseMapper extends BaseMapper<TeacherCourse> {

    @CascadingDeleteList(
            value = {@CascadingDelete(beanType = ClassScheduleCardMapper.class, methodName = "deleteByTeacherCourseIdList")},
            selectMethod = @CascadingPreSelectBeforeDelete(beanType = TeacherCourseMapper.class, methodName = "selectIdListByTeacherIdList", argsClassType = {List.class})
    )
    Integer deleteByTeacherIdList(List<Object> idList);

    List<Integer> selectIdListByTeacherIdList(List<Object> teacherList);

    @CascadingInsertCheckList({
            @CascadingInsertCheck(beanType = TeacherMapper.class,methodName = "selectByIdList",fieldName = "teacherId"),
            @CascadingInsertCheck(beanType = CourseMapper.class,methodName = "selectByIdList",fieldName = "courseId")
    })
    Integer insertList(List<TeacherCourse> teacherCourseList);
}