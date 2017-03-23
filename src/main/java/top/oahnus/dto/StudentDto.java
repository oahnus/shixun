package top.oahnus.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

/**
 * Created by oahnus on 2017/3/9.
 * 21:54
 */
@Data
public class StudentDto {
    private String id;
    @NotEmpty(message = "学生学号不能为空")
    private String studentNum;
    @NotEmpty(message = "学生姓名不能为空")
    private String name;
    @NotEmpty(message = "学生专业不能为空")
    private String profession;
    @NotEmpty(message = "学生学院不能为空")
    private String depart;
    private String sex;
    @Pattern(regexp = "^([a-z0-9A-Z]+[-|\\\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\\\.)+[a-zA-Z]{2,}$")
    private String email;
}
