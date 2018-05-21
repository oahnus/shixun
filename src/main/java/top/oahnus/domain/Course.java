package top.oahnus.domain;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import top.oahnus.enums.CourseState;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotEmpty
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;
    @NotEmpty
    private String professionIds;
    private String memo;
    @NotNull
    private Date startTime;
    @NotNull
    private Date endTime;
    private String addition;
    @NotNull
    private CourseState state = CourseState.COURSE_INIT;
    private Date updateTime;
    private Date createTime;
    private Boolean delFlag = false;

    public Course() {}
}
