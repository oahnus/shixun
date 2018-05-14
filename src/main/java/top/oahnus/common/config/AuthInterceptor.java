package top.oahnus.common.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import top.oahnus.Constants;
import top.oahnus.common.annotations.NeedAdmin;
import top.oahnus.common.annotations.NoAuthNeed;
import top.oahnus.domain.UserAuth;
import top.oahnus.enums.RoleEnum;
import top.oahnus.exception.NoAuthException;
import top.oahnus.service.AuthService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * Created by oahnus on 2017/2/25.
 * 21:28
 */
@Component
@Slf4j
public class AuthInterceptor extends HandlerInterceptorAdapter{
    @Value("${admin.package}")
    private String adminPackage;
    @Value("${mode}")
    private String mode;

    @Autowired
    private AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(handler instanceof HandlerMethod){
            // TODO 真实部署下删除此处
            if (mode.equals("DEV")) {
                return true;
            }
            HandlerMethod method = (HandlerMethod) handler;
            String packageName = method.getBean().getClass().getPackage().getName();
            NoAuthNeed noAuthNeed = method.getMethodAnnotation(NoAuthNeed.class);
            NeedAdmin needAdmin = method.getMethodAnnotation(NeedAdmin.class);
            if (noAuthNeed != null) {
                return true;
            }

            String token = "";
            Cookie[] cookies = request.getCookies();
            for (Cookie c: cookies) {
                if (c.getName().equals("token")) {
                    token = c.getValue();
                }
            }

            UserAuth auth = authService.getAuthByToken(token);
            if (auth == null) {
                throw new NoAuthException("AUTH FAILED");
            }
            if (adminPackage.equals(packageName) || needAdmin != null) {
                if (!auth.getRole().equals(RoleEnum.ADMIN)) {
                    throw new NoAuthException("AUTH FAILED");
                }
            }
            request.setAttribute("authId", auth.getId());
            return true;
        }
        return true;
    }
}
