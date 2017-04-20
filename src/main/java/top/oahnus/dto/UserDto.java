package top.oahnus.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import top.oahnus.enums.AuthType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by oahnus on 2017/4/18
 * 12:17.
 */
@Data
public class UserDto {
    @NotEmpty(message = "用户名不能为空")
    private String username;
    @NotEmpty(message = "旧密码不能为空")
    private String oldPassword;
    @NotEmpty(message = "新密码不能为空")
    @Length(min = 6, max = 16, message = "密码长度要6-16个字符")
    private String newPassword;
    @NotNull(message = "角色类型不能为空")
    private AuthType authType;
}
