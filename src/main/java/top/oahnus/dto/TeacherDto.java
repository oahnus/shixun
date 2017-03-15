package top.oahnus.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by oahnus on 2017/3/11.
 * 21:35
 */
@Data
public class TeacherDto {
    private Long id;
    @NotEmpty(message = "教师工号不能为空")
    private String workerId;
    @NotEmpty(message = "教师姓名不能为空")
    private String name;
    @NotEmpty(message = "教师专业不能为空")
    private String profession;
    @NotEmpty(message = "教师学院不能为空")
    private String depart;
}
