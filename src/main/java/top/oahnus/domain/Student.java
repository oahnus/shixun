package top.oahnus.domain;

import lombok.Data;
import top.oahnus.enums.SexEnum;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by oahnus on 2018/5/10
 * 23:42.
 */
@Data
@Entity(name = "student")
public class Student extends UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String stuNumber;
    private String name;
    private SexEnum sex;
    @OneToOne
    @JoinColumn(name = "profession_id")
    private Profession profession;
    @OneToOne
    @JoinColumn(name = "depart_id")
    private Department department;

    private Long authId;
    private String email;

    private Date createTime;
    private Date updateTime;
    private Boolean delFlag = false;
    @Transient
    private String token;
}
