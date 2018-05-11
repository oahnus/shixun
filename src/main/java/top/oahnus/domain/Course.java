package top.oahnus.domain;

import lombok.Data;
import org.apache.ibatis.annotations.One;
import top.oahnus.enums.CourseState;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by oahnus on 2017/3/19.
 * 课程
 */
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private Long teacherId;
    private Long companyId;
    @OneToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    @OneToOne
    @JoinColumn(name = "company_id")
    private Company company;

    private String professionIds;
    private String memo;
    private Date startTime;
    private Date endTime;
    private String addition;
    private CourseState state;
    private Date updateTime;
    private Date createTime;
    private Boolean delFlag = false;

    public Course() {}
}
