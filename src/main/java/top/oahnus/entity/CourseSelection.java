package top.oahnus.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by oahnus on 2017/3/19.
 * 选课信息
 */
@Data
public class CourseSelection {
    private String id;
    private String courseId;
    private String studentId;
    private Date courseUpdateTime;
    private Date editTime;

    public CourseSelection() {}

    public CourseSelection(String courseId, String studentId, Date courseUpdateTime) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.courseUpdateTime = courseUpdateTime;
    }
}
