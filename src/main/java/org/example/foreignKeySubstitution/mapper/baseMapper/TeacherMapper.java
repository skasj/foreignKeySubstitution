package org.example.foreignKeySubstitution.mapper.baseMapper;

import org.example.foreignKeySubstitution.annotation.Cascading;
import org.example.foreignKeySubstitution.annotation.CascadingDelete;
import org.example.foreignKeySubstitution.annotation.CascadingDeleteList;
import org.example.foreignKeySubstitution.annotation.CascadingPreSelectBeforeDelete;
import org.example.foreignKeySubstitution.modal.entity.Teacher;

import java.util.List;

@Cascading
public interface TeacherMapper extends BaseMapper<Teacher> {

    int insert(Teacher record);

    Teacher selectByPrimaryKey(Integer id);

    List<Teacher> selectByIdList(List<Object> idList);

    List<Integer> selectIdListByNameAndTelephone(String name, String telephone);

    // 级联删除
    @CascadingDeleteList(value = {
            @CascadingDelete(beanType = TeacherCourseMapper.class, methodName = "deleteByTeacherIdList")
    })
    Integer deleteByIdList(List<Object> idList);

    // 级联删除
    @CascadingDeleteList(
            value = {@CascadingDelete(beanType = TeacherCourseMapper.class, methodName = "deleteByTeacherIdList")},
            selectMethod = @CascadingPreSelectBeforeDelete(beanType = TeacherMapper.class, methodName = "selectIdListByNameAndTelephone", argsClassType = {String.class, String.class})
    )
    Integer deleteByNameAndTelephone(String name, String telephone);

    Integer batchUpdateById(List<Teacher> teacherList);

}