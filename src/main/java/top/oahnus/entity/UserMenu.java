package top.oahnus.entity;

import lombok.Data;
import top.oahnus.enums.AuthType;

import java.util.List;

/**
 * Created by oahnus on 2017/4/7
 * 13:58.
 */
@Data
public class UserMenu {
    private int id;
    private String keyName;
    private String name;
    private String icon;
    private String href;
    private AuthType type;
    private int parentId;
    private List<UserMenu> child;
}
