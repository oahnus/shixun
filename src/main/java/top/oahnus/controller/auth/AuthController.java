package top.oahnus.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.oahnus.common.annotations.NoAuthNeed;
import top.oahnus.common.dto.ResultData;
import top.oahnus.common.interfaces.HttpMixin;
import top.oahnus.common.payload.AuthPayload;
import top.oahnus.common.payload.ResetPwdPayload;
import top.oahnus.domain.UserInfo;
import top.oahnus.service.AuthService;

import javax.security.auth.message.AuthException;

/**
 * Created by oahnus on 2017/2/26.
 * 22:46
 */
@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController implements HttpMixin {

    @Autowired
    private AuthService authService;

    @NoAuthNeed
    @PostMapping("/login")
    public ResultData login(@Validated @RequestBody AuthPayload payload){
        UserInfo userInfo = authService.login(payload);
        return new ResultData().data("userInfo", userInfo);
    }

    @PutMapping("/reset/pwd")
    public ResultData resetPass(@Validated @RequestBody ResetPwdPayload payload) {
        authService.ResetPassword(payload);
        return new ResultData();
    }
}
