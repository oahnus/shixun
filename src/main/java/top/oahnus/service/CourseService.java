package top.oahnus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import top.oahnus.common.config.RedisDao;
/**
 * Created by oahnus on 2017/4/3
 * 22:55.
 */
@Service
public class CourseService {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private RedisDao redisDao;


}
