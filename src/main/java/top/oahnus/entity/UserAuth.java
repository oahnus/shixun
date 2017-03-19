package top.oahnus.entity;

import lombok.Data;
import top.oahnus.enums.AuthType;

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
}
