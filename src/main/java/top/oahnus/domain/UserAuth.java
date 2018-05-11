package top.oahnus.domain;

import lombok.Data;
import top.oahnus.enums.RoleEnum;
import top.oahnus.util.MD5Util;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by oahnus on 2017/2/26.
 * 用户权限
 */
@Data
@Entity(name="user_auth")
public class UserAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    // AuthType 角色类型
    private RoleEnum role;
    // 登陆用户的信息
    private Date createTime;

    public static UserAuth buildByWorkerId(String workerId) {
        UserAuth auth = new UserAuth();
        auth.setRole(RoleEnum.TEACHER);
        auth.setUsername(workerId);
        auth.setPassword(MD5Util.getMD5(workerId));
        auth.setCreateTime(new Date());
        return auth;
    }

    public static UserAuth buildByEmail(String email) {
        UserAuth auth = new UserAuth();
        auth.setRole(RoleEnum.COMPANY);
        auth.setUsername(email);
        auth.setPassword(MD5Util.getMD5(email));
        auth.setCreateTime(new Date());
        return auth;
    }

    public static UserAuth buildByStuNumber(String stuNumber) {
        UserAuth auth = new UserAuth();
        auth.setRole(RoleEnum.STUDENT);
        auth.setUsername(stuNumber);
        auth.setPassword(MD5Util.getMD5(stuNumber));
        auth.setCreateTime(new Date());
        return auth;
    }
}
