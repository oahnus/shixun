package top.oahnus.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import java.util.Date;

/**
 * Created by oahnus on 2018/5/14
 * 9:57.
 */
@Data
@Entity(name = "course_task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long courseId;
    private String content;
    private Date startTime;
    @Future(message = "endTime must be later than today")
    private Date endTime;
    private String memo;
    private Long createUserId;
    private Date createTime;
    private Date updateTime;
    private Boolean delFlag = false;
}
