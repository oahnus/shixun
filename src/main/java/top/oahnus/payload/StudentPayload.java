package top.oahnus.payload;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

/**
 * Created by oahnus on 2017/3/9.
 * 21:54
 */
@Data
public class StudentPayload {
    private Long id;
    @NotEmpty(message = "学生学号不能为空")
    private String studentNum;
    @NotEmpty(message = "学生姓名不能为空")
    private String name;
    @NotEmpty(message = "学生专业不能为空")
    private Long professionId;
    @NotEmpty(message = "学生学院不能为空")
    private Long departId;
    private String sex;
    @Pattern(regexp = "^[a-z_0-9.-]{1,64}@([a-z0-9-]{1,200}.){1,5}[a-z]{1,6}$", message = "邮箱格式错误")
    private String email;
}
