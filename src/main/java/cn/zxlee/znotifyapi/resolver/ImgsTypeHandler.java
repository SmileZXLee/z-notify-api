package cn.zxlee.znotifyapi.resolver;

import cn.zxlee.znotifyapi.utils.ConvertUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @program: z-notify-api
 * @description: img_urls字段数组与数据库字符串互相转换处理器
 * @author: zxlee
 * @create: 2022-12-14 21:26
 **/

@MappedTypes(List.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class ImgsTypeHandler extends BaseTypeHandler<List> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List list, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, ConvertUtils.imgList2Str(list));
    }

    @Override
    public List getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String imgStr = resultSet.getString(s);
        return ConvertUtils.imgStr2List(imgStr);
    }

    @Override
    public List getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String imgStr = resultSet.getString(i);
        return ConvertUtils.imgStr2List(imgStr);
    }

    @Override
    public List getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String imgStr = callableStatement.getString(i);
        return ConvertUtils.imgStr2List(imgStr);
    }
}
