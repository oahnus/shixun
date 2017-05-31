package top.oahnus.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import top.oahnus.entity.UserAuth;
import top.oahnus.enums.AuthType;
import top.oahnus.payload.UserAuthPayload;
import top.oahnus.repository.UserAuthRepository;
import top.oahnus.util.MD5Util;

import static org.junit.Assert.*;

/**
 * Created by oahnus on 2017/5/26
 * 15:02.
 */
@SpringBootTest
@EnableAutoConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class UserAuthServiceTest {
    @Autowired
    private UserAuthRepository userAuthRepository;
    @Autowired
    private UserAuthService service;

    @Test
    public void getUserAuth() throws Exception {
        UserAuth userAuth = userAuthRepository.findByUsernameAndPasswordAndType(
                "admin",
                MD5Util.getMD5("111111"),
                AuthType.ADMIN);
        System.out.println(userAuth);
    }

    @Test
    @Transactional
    public void resetPassword() throws Exception {
        Integer count = userAuthRepository.resetPassword(
                "e93b55632a8811e7a53c80fa5b3ea16e",
                MD5Util.getMD5("123456"),
                MD5Util.getMD5("admin"),
                AuthType.ADMIN);
        System.out.println(count);
    }

    @Test
    public void login(){
        UserAuthPayload payload = new UserAuthPayload();
        payload.setAuthType(AuthType.COMPANY);
        payload.setUsername("测试企业一");
        payload.setPassword("123456");
        UserAuth userAuth = service.getUserAuth(payload);
        System.out.println(userAuth);
    }
}