package top.oahnus.domain;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
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
    @NotEmpty
    private String name;
    @NotNull
    private Long courseId;
    @NotEmpty
    private String content;
    @NotNull
    private Date startTime;
    @Future(message = "endTime must be later than today")
    @NotNull
    private Date endTime;
    private String memo;
    private Long createUserId;
    private Date createTime;
    private Date updateTime;
    private Boolean delFlag = false;
}
