package top.oahnus.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by oahnus on 2018/5/14
 * 11:16.
 */
@Data
@Entity(name = "course_selection")
public class SelectionRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long courseId;
    private Long studentId;
    private Date createTime;
    private Date updateTime;
    private Boolean delFlag = false;
}
