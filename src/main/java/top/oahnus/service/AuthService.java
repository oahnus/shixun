package top.oahnus.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.oahnus.common.config.RedisDao;
import top.oahnus.common.interfaces.HttpMixin;
import top.oahnus.common.payload.AuthPayload;
import top.oahnus.common.payload.ResetPwdPayload;
import top.oahnus.enums.RoleEnum;
import top.oahnus.domain.*;
import top.oahnus.exception.NoAuthException;
import top.oahnus.repository.*;
import top.oahnus.util.MD5Util;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.UUID;

/**
 * Created by oahnus on 2017/2/26.
 * 0:23
 */
@Service
@Slf4j
public class AuthService implements HttpMixin {
    @Autowired
    private RedisDao redisDao;
    @Autowired
    private UserAuthRepo authRepo;
    @Autowired
    private AdminRepo adminRepo;
    @Autowired
    private CompanyRepo companyRepo;
    @Autowired
    private TeacherRepo teacherRepo;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private MenuService menuService;

    private final String TOKEN_PREFIX = "token:";

    public UserInfo login(AuthPayload payload) {
        String username = payload.getUsername();
        String password = MD5Util.getMD5(payload.getPassword());
        UserAuth auth = authRepo.findFirstByUsername(username);
        if (auth == null) {
            throw new NoAuthException("");
        }
        String pwd = auth.getPassword();
        if (!pwd.equals(password)) {
            throw new NoAuthException("");
        }
        Long authId = auth.getId();
        RoleEnum role = auth.getRole();

        String token = UUID.randomUUID().toString();
        auth.setPassword("");
        redisDao.putBean(TOKEN_PREFIX + token, auth, UserAuth.class);

        log.debug("[AuthService] - token = {}", token);

        UserInfo userInfo;
        switch (role) {
            case ADMIN:
                userInfo = adminRepo.findFirstByAuthId(authId);
                break;
            case COMPANY:
                userInfo = companyRepo.findFirstByAuthIdAndDelFlagFalse(authId);
                break;
            case TEACHER:
                userInfo = teacherRepo.findFirstByAuthId(authId);
                break;
            case STUDENT:
                userInfo = studentRepo.findFirstByAuthId(authId);
                break;
            default:
                throw new NoAuthException("");
        }
        userInfo.setToken(token);
        setCookie(token);

        // get menu
        List<UserMenu> menuList = menuService.getRoleMenuList(role);
        userInfo.setMenuList(menuList);
        return userInfo;
    }

    public void ResetPassword(ResetPwdPayload payload) {
        String oldPwd = MD5Util.getMD5(payload.getOldPassword());
        String newPwd = MD5Util.getMD5(payload.getNewPassword());

        Long authId = curAuthId();
        UserAuth auth = authRepo.findOne(authId);
        if (!oldPwd.equals(auth.getPassword())) {
            throw new NoAuthException("");
        }
        auth.setPassword(newPwd);
        authRepo.save(auth);
    }

    public UserAuth getAuthByToken(String token) {
        return redisDao.getBean(TOKEN_PREFIX + token, UserAuth.class);
    }

    private void setCookie(String token) {
        Cookie cookie = new Cookie("shixun_token", token);
        response().addCookie(cookie);
    }
}
