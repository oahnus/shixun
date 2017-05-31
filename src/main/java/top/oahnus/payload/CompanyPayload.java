package top.oahnus.payload;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

/**
 * Created by oahnus on 2017/3/23 20:56.
 * 公司
 */
@Data
public class CompanyPayload {
    private Long id;
    @NotEmpty(message = "公司名称不能为空")
    private String name;
    @NotEmpty(message = "公司联系人不能为空")
    private String contact;
    @Pattern(regexp = "^(1[1-9][0-9]{9})$", message = "手机号码格式错误")
    private String contactPhone;
    private String address;
    @Pattern(regexp = "^[a-z_0-9.-]{1,64}@([a-z0-9-]{1,200}.){1,5}[a-z]{1,6}$", message = "邮箱格式错误")
    private String email;
}
