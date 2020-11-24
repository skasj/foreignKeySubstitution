package org.example.foreignKeySubstitution.mapper.baseProvider;

import com.google.common.base.Joiner;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;
import org.example.foreignKeySubstitution.mapper.providerUtils.DeleteSQLAssembler;
import org.example.foreignKeySubstitution.mapper.providerUtils.InsertSQLAssembler;
import org.example.foreignKeySubstitution.mapper.providerUtils.SelectSQLAssembler;
import org.example.foreignKeySubstitution.modal.entity.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TeacherProvider implements ProviderMethodResolver {

    private static final String TABLE_NAME = "teacher";
    private static final Class<?> JAVA_TYPE = Teacher.class;

    public String insert(@Param("record") Teacher record) {
        return InsertSQLAssembler.insertRecord(record, TABLE_NAME);
    }

    public String selectByIdList(@Param("idList") List<Object> idList) {
        return SelectSQLAssembler.selectByIdList(idList, TABLE_NAME, JAVA_TYPE);
    }

    public String deleteByIdList(@Param("idList") List<Object> idList) {
        return DeleteSQLAssembler.deleteByIdList(idList, TABLE_NAME);
    }

    public String countByIdList(@Param("idList") List<Object> idList) {
        return SelectSQLAssembler.countByIdList(idList, TABLE_NAME);
    }

    /**
     * 写法1：
     * update语句之间用';'作为分隔符，返回的字符串形如：
     * UPDATE teacher SET name=#{teacherList[0].name} WHERE (id=#{teacherList[0].id});
     * UPDATE teacher SET name=#{teacherList[1].name} WHERE (id=#{teacherList[1].id})
     * 写法2：
     *   update teacher
     *   set name = (
     *    case
     *    when id = #{teacherList[0].id} then #{teacherList[0].name}
     *    when id = #{teacherList[1].id} then #{teacherList[1].name}
     *    end
     *   ),telephone = (
     *    case
     *    when id = #{teacherList[0].id} then #{teacherList[0].telephone}
     *    when id = #{teacherList[1].id} then #{teacherList[1].telephone}
     *    end
     *    )
     *   where id in (#{teacherList[0].id},#{teacherList[1].id})
     * 写法3：
     *  UPDATE teacher a JOIN
     *  (
     *    SELECT #{teacherList[0].id} AS id, #{teacherList[0].name} AS name, #{teacherList[0].telephone} AS telephone
     *    UNION ALL
     *    SELECT #{teacherList[1].id} AS id, #{teacherList[1].name} AS name, #{teacherList[1].telephone} AS telephone
     *  ) b USING(id)
     *  SET a.name=b.name,a.telephone=b.telephone
     */
    public String batchUpdateById(@Param("teacherList") List<Teacher> teacherList) {
        List<String> updateSQLList = new ArrayList<>();
        IntStream.range(0, teacherList.size())
                .forEach(i -> updateSQLList.add(new SQL().UPDATE(TABLE_NAME)
                        .SET("name=#{teacherList[" + i + "].name}")
                        .WHERE("id=#{teacherList[" + i + "].id}")
                        .toString()));
        return Joiner.on(";")
                .join(updateSQLList);
    }
}
