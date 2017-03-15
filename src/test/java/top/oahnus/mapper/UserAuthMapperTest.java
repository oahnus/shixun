package top.oahnus.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.oahnus.entity.UserAuth;

import static org.junit.Assert.*;

/**
 * Created by oahnus on 2017/3/16.
 * 1:04
 */
@SpringBootTest
@EnableAutoConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class UserAuthMapperTest {
    @Autowired
    private UserAuthMapper userAuthMapper;

    @Test
    public void login() throws Exception {
        UserAuth userAuth = userAuthMapper.login("1341901120","ee97401d19107838eb978f461e354afd",3);
        System.out.println(userAuth.getType());
    }
}