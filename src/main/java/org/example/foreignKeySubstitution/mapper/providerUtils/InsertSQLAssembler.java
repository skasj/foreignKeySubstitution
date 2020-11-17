package org.example.foreignKeySubstitution.mapper.providerUtils;

import org.apache.ibatis.jdbc.SQL;
import org.example.foreignKeySubstitution.utils.NameTransferUitl;

import java.lang.reflect.Field;

public class InsertSQLAssembler {

    public static String insertRecord(Object record, String tableName) {
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
