package org.example.foreignKeySubstitution.mapper.baseProvider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

/**
 * {@link org.example.foreignKeySubstitution.mapper.baseMapper.ClassMapper}
 */
public class ClassProvider implements ProviderMethodResolver {

    String tableName = "class";

    public String insert() {
        return new SQL().INSERT_INTO(tableName)
                .VALUES("id", "#{record.id}")
                .VALUES("level", "#{record.level}")
                .VALUES("serial", "#{record.serial}")
                .toString();
    }

//    public String selectByPrimaryKey(@Param("id") Integer id) {
//        return new SQL().SELECT("id,level,serial")
//                .FROM(tableName)
//                .WHERE("id=#{id}")
//                .toString();
//    }
}
