package top.oahnus.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * Created by oahnus on 2017/2/27.
 * 20:17
 */
@Entity(name = "company")
@Data
public class Company extends UserInfo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyId", fetch = FetchType.EAGER)
    private List<Contact> contactList;

    private String address;
    private String telephone;
    @NotEmpty(message = "email not empty")
    @Email(message = "invalid email format")
    private String email;
    private Long authId;
    private Date createTime;
    private Date updateTime;
    private Boolean delFlag = false;
    @Transient
    private String token;

    public Company(){}
}
