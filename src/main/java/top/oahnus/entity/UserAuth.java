package top.oahnus.entity;

import lombok.Data;
import top.oahnus.enums.AuthType;

/**
 * Created by oahnus on 2017/2/26.
 */
@Data
public class UserAuth {
    private Long id;

    private String username;

    private String password;

    private AuthType type;
}
