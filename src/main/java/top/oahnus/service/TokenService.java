package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import top.oahnus.enums.AuthType;
import top.oahnus.exception.NoAuthException;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by oahnus on 2017/2/26.
 */
@Service
public class TokenService {
    // 7 days
    // 过期时间
    public long expire = 7;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public String setToken(String username, AuthType type) {
        String token = UUID.randomUUID().toString();
        String key = "token:"+token;
        redisTemplate.opsForValue().set(key, username+type.ordinal());
        redisTemplate.expire(key, expire, TimeUnit.DAYS);
        return token;
    }

    public String getUsernameAndTypeByToken(String token) {
        String username = redisTemplate.opsForValue().get("token:"+token);
        if(username == null) throw new NoAuthException("Illegal Token");
        return username;
    }
}
