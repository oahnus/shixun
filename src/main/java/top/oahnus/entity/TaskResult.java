package top.oahnus.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by oahnus on 2017/3/19 20:11.
 * 任务成果
 */
@Data
public class TaskResult {
    private String id;
    private String taskId;
    private String studentId;
    // 任务成果文件的URL
    private String content;
    // 描述，备注
    private String memo;
    private Date createTime;
}
