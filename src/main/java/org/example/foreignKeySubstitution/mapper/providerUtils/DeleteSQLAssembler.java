package org.example.foreignKeySubstitution.mapper.providerUtils;

import com.google.common.base.Joiner;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

public class DeleteSQLAssembler {

    public static String deleteByIdList(List<Object> idList, String tableName) {
        Joiner joiner = Joiner.on("','");
        return new SQL().DELETE_FROM(tableName)
                .WHERE("id in ('" + joiner.join(idList) + "')")
                .toString();
    }
}
