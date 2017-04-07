package top.oahnus.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.oahnus.entity.UserMenu;
import top.oahnus.enums.AuthType;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by oahnus on 2017/4/7
 * 17:57.
 */
@SpringBootTest
@EnableAutoConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMenuMapperTest {
    @Autowired
    private UserMenuMapper userMenuMapper;

    @Test
    public void selectUserMenuByAuthType(){
        List<UserMenu> userMenus = userMenuMapper.selectRootUserMenuByAuthType(AuthType.STUDENT.ordinal());
        userMenus.forEach(userMenu -> userMenu.setChild(userMenuMapper.selectChildUserMenuByParentId(userMenu.getId())));
        System.out.println(userMenus);
    }
}