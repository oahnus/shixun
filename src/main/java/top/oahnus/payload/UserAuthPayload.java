package top.oahnus.payload;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import top.oahnus.enums.AuthType;

import javax.validation.constraints.NotNull;

/**
 * Created by oahnus on 2017/2/26.
 * 21:31
 */
@Data
public class UserAuthPayload {
    private String id;
    @NotEmpty(message = "用户名不能为空")
    private String username;
    @NotEmpty(message = "密码不能为空")
    private String password;
    // auth type
    @NotNull(message = "登陆用户类型不能为空")
    private AuthType authType;
}
