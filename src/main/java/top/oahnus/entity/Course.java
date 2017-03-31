package top.oahnus.entity;

import lombok.Data;
import top.oahnus.dto.CourseDto;
import top.oahnus.enums.CourseState;

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
    private Date endTime;
    // 课程附件在服务器上的URL
    private String addition;
    // 课程信息更新时间
    private Date updateTime;
    // 开课状态
    private CourseState state;

    public Course() {}

    public Course(CourseDto courseDto) {
        this.name = courseDto.getName();
        this.teacherId = courseDto.getTeacherId();
        this.companyId = courseDto.getCompanyId();
        this.professions = courseDto.getProfessions();
        this.memo = courseDto.getMemo();
        this.startTime = courseDto.getStartTime();
        this.endTime = courseDto.getEndTime();
        this.addition = courseDto.getAddition();
        this.updateTime = new Date();
        this.state = CourseState.valueOf(courseDto.getState());
    }
}
