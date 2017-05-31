package top.oahnus.entity;

import lombok.Data;
import top.oahnus.enums.AuthType;

import javax.persistence.*;
import java.util.List;

/**
 * Created by oahnus on 2017/2/26.
 * 用户权限
 */
@Data
@Entity(name = "user_auth")
public class UserAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    // AuthType 角色类型
    private AuthType type;
    // 登陆用户的信息
    @Transient
    private User user;
    @Transient
    private List<UserMenu> userMenus;
}
