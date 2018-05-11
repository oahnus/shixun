package top.oahnus.common.payload;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by oahnus on 2018/5/11
 * 16:10.
 */
@Data
public class ResetPwdPayload {
//    @NotEmpty(message = "username not empty")
//    private String username;
    @NotEmpty(message = "old password not empty")
    private String oldPassword;
    @NotEmpty(message = "new password not empty")
    private String newPassword;
}
