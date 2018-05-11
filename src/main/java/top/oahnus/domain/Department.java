package top.oahnus.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by oahnus on 2018/5/10
 * 16:00.
 */
@Data
@Entity(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Transient
    private List<Profession> professions;
    private Date createTime;
    private Date updateTime;
    private Boolean delFlag = false;
}
