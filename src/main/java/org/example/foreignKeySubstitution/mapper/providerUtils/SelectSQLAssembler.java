package org.example.foreignKeySubstitution.mapper.providerUtils;

import com.google.common.base.Joiner;
import org.apache.ibatis.jdbc.SQL;
import org.example.foreignKeySubstitution.utils.NameTransferUitl;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class SelectSQLAssembler {
    public static String selectByIdList(List<Object> idList, String tableName, Class<?> resultType) {
        Joiner joiner = Joiner.on("','");
        Field[] declaredFields = resultType.getDeclaredFields();
        return new SQL().SELECT(Arrays.stream(declaredFields)
                .filter(f -> !"serialVersionUID".equals(f.getName()))
                .map(f -> NameTransferUitl.humpToUnderline(f.getName()))
                .distinct()
                .toArray(String[]::new))
                .FROM(tableName)
                .WHERE("id in ('" + joiner.join(idList) + "')")
                .toString();
    }
}
