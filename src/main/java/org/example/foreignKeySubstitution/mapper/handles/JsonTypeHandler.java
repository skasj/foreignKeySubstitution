package org.example.foreignKeySubstitution.mapper.handles;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.example.foreignKeySubstitution.utils.JsonUtil;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@MappedTypes({List.class, Map.class})
public class JsonTypeHandler<E> extends BaseTypeHandler<E> {

    private final Class<E> type;

    public JsonTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        } else {
            this.type = type;
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType)
        throws SQLException {
        ps.setObject(i, JsonUtil.toJsonString(parameter));
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        E res = null;
        try {
            String s = rs.getString(columnName);
            if (StringUtils.isNotEmpty(s)) {
                res = JsonUtil.parseObject(s, this.type);
            }
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw new SQLException(e.getCause());
        }
        return res;
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        E res = null;
        try {
            String s = rs.getString(columnIndex);
            if (StringUtils.isNotEmpty(s)) {
                res = JsonUtil.parseObject(s, this.type);
            }
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw new SQLException(e.getCause());
        }
        return res;
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        E res = null;
        try {
            String s = cs.getString(columnIndex);
            if (StringUtils.isNotEmpty(s)) {
                res = JsonUtil.parseObject(s, this.type);
            }
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw new SQLException(e.getCause());
        }
        return res;
    }
}
