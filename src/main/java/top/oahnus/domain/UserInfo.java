package top.oahnus.domain;

import lombok.Data;

import java.util.List;

/**
 * Created by oahnus on 2018/5/9
 * 17:12.
 */
@Data
public abstract class UserInfo {
    protected List<UserMenu> menuList;
    protected String token;
}
