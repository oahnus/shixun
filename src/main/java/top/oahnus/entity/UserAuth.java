package top.oahnus.entity;

import lombok.Data;
import top.oahnus.enums.AuthType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * Created by oahnus on 2017/2/26.
 * 用户权限
 */
@Data
public class UserAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    // AuthType 角色类型
    private Integer roleId;
    @Transient
    private String roleName;
    // 登陆用户的信息
    private Date createTime;
}
