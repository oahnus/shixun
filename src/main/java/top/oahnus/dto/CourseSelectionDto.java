package top.oahnus.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by oahnus on 2017/4/8
 * 13:25.
 */
@Data
public class CourseSelectionDto {
    private String id;
    @NotEmpty(message = "课程id不能为空")
    private String courseId;
    @NotEmpty(message = "学生id不能为空")
    private String studentId;
}
