package top.oahnus.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by oahnus on 2017/3/19.
 * Admin
 */
@Data
@Entity(name = "admin")
public class Admin extends UserInfo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String telephone;
    private String email;
    private Date createTime;
    private Long authId;

    @Transient
    private String token;
}
