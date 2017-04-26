package top.oahnus.entity;

import lombok.Data;
import top.oahnus.enums.AuthType;

/**
 * Created by oahnus on 2017/4/25
 * 15:22.
 */
@Data
public class User {
    private String username;
    private AuthType type;
    private String name;
}
