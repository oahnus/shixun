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
    private PageForm page;
    private String name;
    private Long teacherId;
    private Long companyId;
    private Long professionId;
    private CourseState state;
}
