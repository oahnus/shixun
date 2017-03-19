package top.oahnus.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.oahnus.dto.TokenDto;
import top.oahnus.dto.UserAuthDto;
import top.oahnus.entity.UserAuth;
import top.oahnus.service.TokenService;
import top.oahnus.service.UserAuthService;

/**
 * Created by oahnus on 2017/2/26.
 * 22:46
 */
@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private TokenService tokenService;

    /**
     * 根据用户的username和auth type生成token，保存在redis中
     * @param userAuthDto userAuthDto
     * @return Token
     */
    @PostMapping("/auth")
    public TokenDto login(@Validated @RequestBody UserAuthDto userAuthDto){
        UserAuth userAuth = userAuthService.getUserAuth(userAuthDto);
        String token = tokenService.setToken(userAuth.getUsername(), userAuth.getType());
        return new TokenDto(token);
    }

    // TODO 根据用户留下的邮箱发送验证邮件来修改密码
}
