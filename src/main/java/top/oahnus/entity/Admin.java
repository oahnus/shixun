package top.oahnus.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


/**
 * Created by oahnus on 2017/3/19.
 * Admin
 */
@Data
public class Admin extends UserInfo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String telephone;
    private String email;
    private Date createTime;
    private Long authId;
}
