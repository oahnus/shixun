package top.oahnus.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by oahnus on 2017/3/19.
 * 课程
 */
@Data
public class Course {
    private String id;
    private String name;
    private String teacherId;
    private String companyId;
    // 开设该课程的专业集合,以;间隔
    private String professions;
    // 课程描述
    private String memo;
    // 课程开课时间
    private Date startTime;
    // 课程授课时长
    private Date duration;
    // 课程附件在服务器上的URL
    private String addition;
    // 课程信息更新时间
    private Date updateTime;

    public Course() {}

    public Course(String name, String teacherId, String companyId, String professions, String memo, Date startTime, Date duration, String addition) {
        this.name = name;
        this.teacherId = teacherId;
        this.companyId = companyId;
        this.professions = professions;
        this.memo = memo;
        this.startTime = startTime;
        this.duration = duration;
        this.addition = addition;
    }
}
