package org.example.foreignKeySubstitution.mapper.baseMapper;

import org.example.foreignKeySubstitution.annotation.Cascading;
import org.example.foreignKeySubstitution.annotation.CascadingDelete;
import org.example.foreignKeySubstitution.annotation.CascadingDeleteList;
import org.example.foreignKeySubstitution.annotation.CascadingSelect;
import org.example.foreignKeySubstitution.modal.entity.TeacherCourse;

import java.util.List;

@Cascading
public interface TeacherCourseMapper extends BaseMapper<TeacherCourse> {

    @CascadingDeleteList(
            value = {@CascadingDelete(beanType = ClassScheduleCardMapper.class, methodName = "deleteByTeacherCourseIdList")},
            selectMethod = @CascadingSelect(beanType = TeacherCourseMapper.class, methodName = "selectIdListByTeacherIdList", argsClassType = {List.class})
    )
    Integer deleteByTeacherIdList(List<Object> idList);

    List<Integer> selectIdListByTeacherIdList(List<Object> teacherList);

}