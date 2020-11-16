package org.example.foreignKeySubstitution.mapper.providerUtils;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.example.foreignKeySubstitution.annotation.TableColumn;

import java.lang.reflect.Field;

public class InsertSQLGenerator {

    public String insertRecord(@Param("record") Object record, @Param("tableName") String tableName) {
        // todo 增加本地缓存 或者 使用享元模式
        SQL sql = new SQL().INSERT_INTO(tableName);
        Class<?> aClass = record.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(TableColumn.class))
                sql.VALUES(humpToUnderline(field.getName()), "#{record." + field.getName() + "}");
        }
        return sql.toString();
    }

    /**
     * 驼峰转 下划线
     * userName  ---->  user_name
     * user_name  ---->  user_name
     *
     * @param humpName 驼峰命名
     * @return 带下滑线的String
     */
    public static String humpToUnderline(String humpName) {
        //截取下划线分成数组，
        char[] charArray = humpName.toCharArray();
        StringBuilder buffer = new StringBuilder();
        //处理字符串
        for (int i = 0, l = charArray.length; i < l; i++) {
            if (charArray[i] >= 65 && charArray[i] <= 90) {
                buffer.append("_")
                        .append(charArray[i] += 32);
            } else {
                buffer.append(charArray[i]);
            }
        }
        return buffer.toString();
    }
}
