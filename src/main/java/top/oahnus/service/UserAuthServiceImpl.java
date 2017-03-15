package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.oahnus.dto.UserAuthDto;
import top.oahnus.entity.UserAuth;
import top.oahnus.enums.AuthType;
import top.oahnus.exception.NotFoundException;
import top.oahnus.mapper.UserAuthMapper;
import top.oahnus.util.MD5Util;

/**
 * Created by oahnus on 2017/2/26.
 * 0:23
 */
@Service
public class UserAuthServiceImpl implements UserAuthService {

//    private UserAuthRepository userAuthRepository;
    @Autowired
    private UserAuthMapper userAuthMapper;

    @Override
    public UserAuth getUserAuth(UserAuthDto userAuthDto) {
        UserAuth userAuth = userAuthMapper.login(
                userAuthDto.getUsername(),
                MD5Util.getMD5(userAuthDto.getPassword()),
                AuthType.valueOf(userAuthDto.getAuthType()).ordinal());
        if(userAuth == null){
            throw new NotFoundException("用户不存在");
        }
        return userAuth;
    }
}
