package top.oahnus.domain;

import lombok.Data;
import top.oahnus.enums.SexEnum;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by oahnus on 2017/3/1.
 * 23:23
 */
@Data
@Entity(name = "teacher")
public class Teacher extends UserInfo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String workerId;
    private String name;
    @OneToOne
    @JoinColumn(name = "profession_id")
    private Profession profession;
    @OneToOne
    @JoinColumn(name = "depart_id")
    private Department department;
    // 性别
    private SexEnum sex;
    // 职称
    private String jobTitle;
    private String email;
    private Long authId;

    private Boolean delFlag = false;
    private Date createTime;
    private Date updateTime;

    @Transient
    private String token;

    public Teacher() {}
}
