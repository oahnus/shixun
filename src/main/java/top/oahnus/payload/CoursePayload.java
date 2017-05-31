package top.oahnus.payload;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Future;
import java.util.Date;

/**
 * Created by oahnus on 2017/3/31
 * 15:46.
 */
@Data
public class CoursePayload {
    private Long id;
    @NotEmpty(message = "课程名不能为空")
    private String name;
    @NotEmpty(message = "教师Id不能为空")
    private Long teacherId;
    @NotEmpty(message = "公司ID不能为空")
    private Long companyId;
    @NotEmpty(message = "可选专业不能为空")
    private String professions;
    private String memo;
    private String addition;
    @Future(message = "课程开始时间要大于当前时间")
    private Date startTime;
    @Future(message = "课程结束时间要大于当前时间")
    private Date endTime;
    @NotEmpty(message = "课程状态信息不能为空")
    private String state;
}
