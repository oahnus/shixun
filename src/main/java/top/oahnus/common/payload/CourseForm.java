package top.oahnus.common.payload;

import lombok.Data;
import top.oahnus.enums.CourseState;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by oahnus on 2018/5/14
 * 11:03.
 */
@Data
public class CourseForm {
    @Size(min = 1, message = "courseId list not empty")
    @NotNull(message = "courseId list not null")
    private List<Long> courseIds;
    @NotNull(message = "state not null")
    private CourseState state;
}
