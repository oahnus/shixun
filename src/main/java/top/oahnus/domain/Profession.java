package top.oahnus.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by oahnus on 2018/5/10
 * 15:59.
 */
@Data
@Entity(name = "profession")
public class Profession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long departId;
    private Date createTime;
    private Date updateTime;
    private Boolean delFlag = false;
}
