package top.oahnus.entity;

import lombok.Data;
import top.oahnus.dto.CourseTaskDto;

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

    public CourseTask() {}

    public CourseTask(CourseTaskDto courseTaskDto) {
        this.id = courseTaskDto.getId();
        this.courseId = courseTaskDto.getCourseId();
        this.name = courseTaskDto.getName();
        this.content = courseTaskDto.getContent();
        this.startTime = courseTaskDto.getStartTime();
        this.endTime = courseTaskDto.getEndTime();
        this.memo = courseTaskDto.getMemo();
    }
}
