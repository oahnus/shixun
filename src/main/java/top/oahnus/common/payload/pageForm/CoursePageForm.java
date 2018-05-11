package top.oahnus.common.payload.pageForm;

import lombok.Data;
import top.oahnus.enums.CourseState;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by oahnus on 2018/5/10
 * 11:11.
 */
@Data
public class CoursePageForm {
    private String name;
    private Long professionId;
    private CourseState state;
    private Long teacherId;
    private Long companyId;
    @NotNull
    @Min(0)
    private Integer page = 0;
    @NotNull
    @Min(5)
    private Integer size = 10;
}
