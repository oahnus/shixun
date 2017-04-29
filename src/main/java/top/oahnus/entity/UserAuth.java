package top.oahnus.entity;

import lombok.Data;
import top.oahnus.enums.AuthType;

import java.util.List;

/**
 * Created by oahnus on 2017/2/26.
 * 用户权限
 */
@Data
public class UserAuth {
    private String id;
    private String username;
    private String password;
    // AuthType 角色类型
    private AuthType type;
    // 登陆用户的信息
    private User user;
    private List<UserMenu> userMenus;
}
