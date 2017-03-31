package top.oahnus.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import top.oahnus.enums.CourseState;

import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

/**
 * Created by oahnus on 2017/3/31
 * 15:46.
 */
@Data
public class CourseDto {
    @NotEmpty(message = "课程名不能为空")
    private String name;
    @NotEmpty(message = "教师Id不能为空")
    private String teacherId;
    @NotEmpty(message = "公司ID不能为空")
    private String companyId;
    @NotEmpty(message = "可选专业不能为空")
    private String professions;
    private String memo;
    @NotEmpty(message = "请上传课程附件")
    private String addition;
    @Future
    private Date startTime;
    @Future
    private Date endTime;
    @NotEmpty(message = "课程状态信息不能为空")
    private String state;
}
