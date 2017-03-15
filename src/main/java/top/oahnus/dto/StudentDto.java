package top.oahnus.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by oahnus on 2017/3/9.
 * 21:54
 */
@Data
public class StudentDto {
    private Long id;
    @NotEmpty(message = "学生学号不能为空")
    private String studentNum;
    @NotEmpty(message = "学生姓名不能为空")
    private String name;
    @NotEmpty(message = "学生专业不能为空")
    private String profession;
    @NotEmpty(message = "学生学院不能为空")
    private String depart;

    StudentDto() {}
}
