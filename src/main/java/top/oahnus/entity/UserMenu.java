package top.oahnus.entity;

import lombok.Data;
import top.oahnus.enums.AuthType;

import javax.persistence.*;
import java.util.List;

/**
 * Created by oahnus on 2017/4/7
 * 13:58.
 */
@Data
@Entity(name = "user_menu")
public class UserMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String keyName;
    private String name;
    private String icon;
    private String href;
    private AuthType authType;
    private Long parentId;
    @Transient
    private List<UserMenu> child;
}
