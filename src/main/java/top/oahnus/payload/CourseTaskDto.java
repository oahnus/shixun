package top.oahnus.payload;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Future;
import java.util.Date;

/**
 * Created by oahnus on 2017/4/23
 * 17:16.
 */
@Data
public class CourseTaskDto {
    private Long id;
    @NotEmpty(message = "courseId不能为空")
    private Long courseId;
    @NotEmpty(message = "任务名称不能为空")
    private String name;
    private String content; // file url
    @Future(message = "请选择大于当前时间的日期")
    private Date startTime;
    @Future(message = "请选择大于当前时间的日期")
    private Date endTime;
    @Future(message = "请选择大于当前时间的日期")
    private Date deadline;
    private String memo;
}
