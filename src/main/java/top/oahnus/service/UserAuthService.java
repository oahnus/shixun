package top.oahnus.service;

import top.oahnus.dto.UserAuthDto;
import top.oahnus.dto.UserDto;
import top.oahnus.entity.UserAuth;

/**
 * Created by oahnus on 2017/2/26.
 */
public interface UserAuthService {
    UserAuth getUserAuth(UserAuthDto userAuthDto);
    Integer resetPassword(UserDto userDto);
}
