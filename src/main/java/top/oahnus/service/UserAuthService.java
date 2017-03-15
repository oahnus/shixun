package top.oahnus.service;

import top.oahnus.dto.UserAuthDto;
import top.oahnus.entity.UserAuth;

/**
 * Created by oahnus on 2017/2/26.
 */
public interface UserAuthService {
    UserAuth getUserAuth(UserAuthDto userAuthDto);
}
