package top.oahnus;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import top.oahnus.exception.NoAuthException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by oahnus on 2017/2/25.
 * 21:28
 */
@Component
@Slf4j
public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Value("${auth.admin.package}")
    private String authAdminPackage;
    @Value("${auth.package}")
    private String authPackage;
    @Value("${mode}")
    private String mode;

    @Autowired
    private StringRedisTemplate template;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(handler instanceof HandlerMethod){
            // TODO 真实部署下删除此处
            if (mode.equals("DEV")) {
                return true;
            }
            HandlerMethod method = (HandlerMethod) handler;
            // filter auth package
            if(authPackage.equals(method.getBean().getClass().getPackage().getName())){
                return true;
            }
            String token = request.getHeader(Constants.TOKEN);

            String username = template.opsForValue().get(keyWithToken(token));
            if (username == null) throw new NoAuthException("Illegal Token");

            int authType = Integer.parseInt(username.substring(username.length()-1));

            // TODO 仅允许管理员权限删除修改数据
//            PutMapping put = method.getMethodAnnotation(PutMapping.class);
//            String[] path = put.path();
//            if (path.equals("/teachers")) { /* do something */}

            switch (authType){
                // admin
                case 0:
                    return true;
                // company
                case 1:
                // teacher
                case 2:
                // student
                case 3:
                    if(!authAdminPackage.equals(method.getBean().getClass().getPackage().getName())){
                        return true;
                    }
                    break;
            }
            throw new NoAuthException("AUTH FAILED");
        }
        return true;
    }

    private String keyWithToken(String token) {
        return "token:" + token;
    }
}
