package top.oahnus.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by oahnus on 2017/3/19.
 * 课程任务
 */
@Data
public class CourseTask {
    private String id;
    private String courseId;
    private String name;
    // 上传的任务内容文件的URL
    private String content;
    private Date startTime;
    private Date endTime;
    private String memo;
}
