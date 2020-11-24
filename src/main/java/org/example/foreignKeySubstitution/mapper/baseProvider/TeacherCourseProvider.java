package org.example.foreignKeySubstitution.mapper.baseProvider;

import com.google.common.base.Joiner;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.example.foreignKeySubstitution.mapper.providerUtils.DeleteSQLAssembler;
import org.example.foreignKeySubstitution.mapper.providerUtils.InsertSQLAssembler;
import org.example.foreignKeySubstitution.mapper.providerUtils.SelectSQLAssembler;
import org.example.foreignKeySubstitution.modal.entity.TeacherCourse;
import org.example.foreignKeySubstitution.utils.NameTransferUitl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TeacherCourseProvider {

    private static final String TABLE_NAME = "teacher_course";
    private static final Class<?> JAVA_TYPE = TeacherCourse.class;

    public String insert(@Param("record") TeacherCourse record) {
        return InsertSQLAssembler.insertRecord(record, TABLE_NAME);
    }

    public String selectByIdList(@Param("idList") List<Object> idList) {
        return SelectSQLAssembler.selectByIdList(idList, TABLE_NAME, JAVA_TYPE);
    }

    public String deleteByIdList(@Param("idList") List<Object> idList) {
        return DeleteSQLAssembler.deleteByIdList(idList, TABLE_NAME);
    }

    public String selectIdListByTeacherIdList(@Param("idList") List<Object> idList) {
        Joiner joiner = Joiner.on(",");
        List<String> idListString = new ArrayList<>();
        IntStream.range(0, idList.size())
                .forEach(id -> idListString.add(String.format("#{idList[%d]}", id)));
        return new SQL().SELECT("id")
                .FROM(TABLE_NAME)
                .WHERE("teacher_id in (" + joiner.join(idListString) + ")")
                .toString();
    }

    public String deleteByTeacherIdList(@Param("idList") List<Object> idList) {
        Joiner joiner = Joiner.on(",");
        List<String> idListString = new ArrayList<>();
        IntStream.range(0, idList.size())
                .forEach(id -> idListString.add(String.format("#{idList[%d]}", id)));
        return new SQL().DELETE_FROM(TABLE_NAME)
                .WHERE("teacher_id in (" + joiner.join(idListString) + ")")
                .toString();
    }

    public String insertList(@Param("recordList") List<TeacherCourse> teacherCourseList){
        Class<?> aClass = teacherCourseList.get(0).getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        List<String> valueFormatStringList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder("insert into ");
        stringBuilder.append(TABLE_NAME).append(" (");
        for (Field field : declaredFields) {
            if (!"serialVersionUID".equals(field.getName())){
                stringBuilder.append(NameTransferUitl.humpToUnderline(field.getName())).append(",");
                valueFormatStringList.add("#{recordList[%d]."+ field.getName()+"}");
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        stringBuilder.append(") values ");
        IntStream.range(0,teacherCourseList.size()).forEach(rowNo->{
            stringBuilder.append("(");
            IntStream.range(0,valueFormatStringList.size()).forEach(columnNo->{
                stringBuilder.append(String.format(valueFormatStringList.get(columnNo),rowNo));
                stringBuilder.append(",");
            });
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
            stringBuilder.append("),");
        });
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }
}
