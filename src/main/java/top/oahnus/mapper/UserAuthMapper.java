package top.oahnus.mapper;

import org.apache.ibatis.annotations.*;
import top.oahnus.entity.UserAuth;
import top.oahnus.enums.AuthType;
import top.oahnus.handler.AuthTypeHandler;

/**
 * Created by oahnus on 2017/3/13.
 * 22:53
 */
@Mapper
public interface UserAuthMapper {

    @Results({
            @Result(column = "id", property = "id", javaType = String.class),
            @Result(column = "username", property = "username", javaType = String.class),
            @Result(column = "type", property = "type", javaType = AuthType.class, typeHandler = AuthTypeHandler.class)
    })
    @Select("SELECT id,username,type FROM user_auth WHERE username = #{username} AND password = #{password} AND type = #{type}")
    UserAuth login(@Param("username") String username, @Param("password") String password, @Param("type") Integer type);
}
