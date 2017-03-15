package top.oahnus.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import top.oahnus.enums.AuthType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by oahnus on 2017/3/13.
 * 23:46
 */
public class AuthTypeHandler implements TypeHandler<AuthType> {
    private Class<AuthType> type;
    private AuthType[] types;

    public AuthTypeHandler(Class<AuthType> type) {
        if (type == null)
            throw new IllegalArgumentException("Type argument cannot be null");
        this.type = type;
        this.types = type.getEnumConstants();
        if (this.types == null)
            throw new IllegalArgumentException(type.getSimpleName()
                    + " does not represent an enum type.");
    }

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, AuthType authType, JdbcType jdbcType) throws SQLException {
        if(authType!=null) {
            preparedStatement.setInt(i,authType.ordinal());
        }
    }

    @Override
    public AuthType getResult(ResultSet resultSet, String s) throws SQLException {
        return AuthType.getType(resultSet.getInt(s));
    }

    @Override
    public AuthType getResult(ResultSet resultSet, int i) throws SQLException {
        return AuthType.getType(resultSet.getInt(i));
    }

    @Override
    public AuthType getResult(CallableStatement callableStatement, int i) throws SQLException {
        return AuthType.getType(callableStatement.getInt(i));
    }
}
