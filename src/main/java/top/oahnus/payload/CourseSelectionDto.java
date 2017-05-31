package top.oahnus.payload;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by oahnus on 2017/4/8
 * 13:25.
 */
@Data
public class CourseSelectionDto {
    private Long id;
    @NotEmpty(message = "课程id不能为空")
    private Long courseId;
    @NotEmpty(message = "学生id不能为空")
    private Long studentId;
}
