package top.oahnus.common.payload;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by oahnus on 2018/5/10
 * 8:42.
 */
@Data
public class AuthPayload {
    @NotEmpty(message = "username not empty")
    private String username;
    @NotEmpty(message = "password not empty")
    private String password;
}
