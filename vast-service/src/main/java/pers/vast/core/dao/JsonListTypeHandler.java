package pers.vast.core.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import pers.vast.core.util.JsonUtils;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * mybatis 实体转 json 数组 handler
 * Created by sengzin on 2018/5/5.
 */
public class JsonListTypeHandler<T> extends BaseTypeHandler<List<T>> {
    private Class javaType;

    public JsonListTypeHandler(Class<T> type) {
        this.javaType = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<T> o, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, JsonUtils.toJsonString(o));
    }

    @Override
    public List<T> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return JsonUtils.parseList(rs.getString(columnName), this.javaType);
    }

    @Override
    public List<T> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return null;//JsonUtils.parse(rs.getString(columnIndex), Object.class);
    }

    @Override
    public List<T> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;//JsonUtils.parse(cs.getString(columnIndex), Object.class);
    }
}
