package top.oahnus.domain;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by oahnus on 2018/5/10
 * 9:45.
 */
@Entity(name = "company_contact")
@Data
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "name is not empty")
    private String name;
    private String email;
    @NotEmpty(message = "telephone is not empty")
    private String telephone;
    private Long companyId;
    private Date createTime;
    private Date updateTime;
}
