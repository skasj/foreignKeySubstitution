package org.example.foreignKeySubstitution.mapper.providerUtils;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;
import org.example.foreignKeySubstitution.utils.NameTransferUitl;

import java.lang.reflect.Field;

public class InsertSQLAssembler implements ProviderMethodResolver {

    public String insertRecord(@Param("record") Object record, @Param("tableName") String tableName) {
        // todo 增加本地缓存 或者 使用享元模式
        SQL sql = new SQL().INSERT_INTO(tableName);
        Class<?> aClass = record.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            if (!"serialVersionUID".equals(field.getName()))
                sql.VALUES(NameTransferUitl.humpToUnderline(field.getName()), "#{record." + field.getName() + "}");
        }
        return sql.toString();
    }
}
