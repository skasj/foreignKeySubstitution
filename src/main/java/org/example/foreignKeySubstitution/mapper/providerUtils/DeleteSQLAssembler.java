package org.example.foreignKeySubstitution.mapper.providerUtils;

import com.google.common.base.Joiner;
import org.apache.ibatis.jdbc.SQL;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class DeleteSQLAssembler {

    public static String deleteByIdList(List<Object> idList, String tableName) {
        Joiner joiner = Joiner.on(",");
        List<String> idListString = new ArrayList<>();
        IntStream.range(0, idList.size())
                .forEach(id -> idListString.add(String.format("#{idList[%d]}", id)));
        return new SQL().DELETE_FROM(tableName)
                .WHERE("id in (" + joiner.join(idListString) + ")")
                .toString();
    }
}
