package top.oahnus.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by oahnus on 2017/4/24
 * 16:28.
 */
@Data
public class TaskResultDto {
    private String id;
    @NotEmpty(message = "任务id不能为空")
    private String taskId;
    @NotEmpty(message = "学生id不能为空")
    private String studentId;
    @NotEmpty(message = "任务成果文件不能为空")
    private String content;
    private String memo;
}
