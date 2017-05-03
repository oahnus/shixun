package top.oahnus.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.oahnus.dto.UserAuthDto;
import top.oahnus.entity.UserAuth;
import top.oahnus.enums.AuthType;

import static org.junit.Assert.*;

/**
 * Created by oahnus on 2017/2/26.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserAuthServiceTest {
    @Autowired
    private UserAuthService userAuthService;

    @Test
    public void getUserAuth() throws Exception {
        UserAuthDto userAuthDto = new UserAuthDto();
        userAuthDto.setUsername("1341901120");
        userAuthDto.setPassword("1341901120");
        userAuthDto.setAuthType("STUDENT");
        UserAuth userAuth = userAuthService.getUserAuth(userAuthDto);
        System.out.println(userAuth.getId());
    }

}