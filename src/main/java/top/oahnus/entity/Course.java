package top.oahnus.entity;

import lombok.Data;
import top.oahnus.payload.CoursePayload;
import top.oahnus.enums.CourseState;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by oahnus on 2017/3/19.
 * 课程
 */
@Data
@Entity(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne
//    @JoinColumn(name = "id")
    private Teacher teacher = new Teacher();
    @OneToOne
//    @JoinColumn(name = "id")
    private Company company = new Company();

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

    public static Course fromPayload(CoursePayload payload) {
        Course course = new Course();
        course.setId(payload.getId());
        course.setName(payload.getName());
        course.setProfessions(payload.getProfessions());
        course.setMemo(payload.getMemo());
        course.setStartTime(payload.getStartTime());
        course.setEndTime(payload.getEndTime());
        course.updateTime = new Date();
        course.setState(CourseState.INIT);

        Teacher teacher = new Teacher();
        teacher.setId(payload.getTeacherId());
        Company company = new Company();
        company.setId(payload.getCompanyId());

        course.setTeacher(teacher);
        course.setCompany(company);
        return course;
    }
}
