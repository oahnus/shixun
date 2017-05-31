package top.oahnus.payload;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

/**
 * Created by oahnus on 2017/3/11.
 * 21:35
 */
@Data
public class TeacherPayload {
    private Long id;
    @NotEmpty(message = "教师工号不能为空")
    private String workerId;
    @NotEmpty(message = "教师姓名不能为空")
    private String name;
    @NotEmpty(message = "教师专业不能为空")
    private Long professionId;
    @NotEmpty(message = "教师学院不能为空")
    private Long departId;
    private String sex;
    // 职称
    private String jobTitle;
    @Pattern(regexp = "^[a-z_0-9.-]{1,64}@([a-z0-9-]{1,200}.){1,5}[a-z]{1,6}$", message = "邮箱格式错误")
    private String email;
}
