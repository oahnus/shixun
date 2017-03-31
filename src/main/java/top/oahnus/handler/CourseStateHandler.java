package top.oahnus.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import top.oahnus.enums.AuthType;
import top.oahnus.enums.CourseState;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by oahnus on 2017/3/13.
 * 23:46
 */
public class CourseStateHandler implements TypeHandler<CourseState> {
    private Class<CourseState> state;
    private CourseState[] states;

    public CourseStateHandler(Class<CourseState> state) {
        if (state == null)
            throw new IllegalArgumentException("Type argument cannot be null");
        this.state = state;
        this.states = state.getEnumConstants();
        if (this.states == null)
            throw new IllegalArgumentException(state.getSimpleName()
                    + " does not represent an enum state.");
    }

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, CourseState state, JdbcType jdbcType) throws SQLException {
        if(state != null) {
            preparedStatement.setInt(i, state.ordinal());
        }
    }

    @Override
    public CourseState getResult(ResultSet resultSet, String s) throws SQLException {
        return CourseState.getState(resultSet.getInt(s));
    }

    @Override
    public CourseState getResult(ResultSet resultSet, int i) throws SQLException {
        return CourseState.getState(resultSet.getInt(i));
    }

    @Override
    public CourseState getResult(CallableStatement callableStatement, int i) throws SQLException {
        return CourseState.getState(callableStatement.getInt(i));
    }
}
