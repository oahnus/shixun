package top.oahnus.service;

import top.oahnus.enums.AuthType;

/**
 * Created by oahnus on 2017/2/26.
 *
 */
public interface TokenService {
    // length 64
    int tokenLength = 64;
    // 7 days
    // 过期时间
    long expire = 7;

    String setToken(String username, AuthType type);

    String getUsernameAndTypeByToken(String token);
}
