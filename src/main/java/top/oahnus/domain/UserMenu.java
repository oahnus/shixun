package top.oahnus.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by oahnus on 2018/5/24
 * 14:28.
 */
@Data
@Entity(name = "user_menu")
public class UserMenu implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String keyName;
    private String name;
    private String icon;
    private String href;
    private Long parentId;
    private Integer roleId;
    private Boolean delFlag = false;

    @Transient
    private List<UserMenu> children;
}
