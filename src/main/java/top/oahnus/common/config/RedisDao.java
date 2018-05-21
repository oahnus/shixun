package top.oahnus.common.config;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by oahnus on 2017/10/3
 * 8:39.
 */
@Configuration
@Slf4j
public class RedisDao {
    private JedisPool jedisPool;
    @Value("${spring.redis.host}")
    private String ip;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.pool.max-active}")
    private int maxTotal;
    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.pool.max-wait}")
    private long waitMill;
    @Value("${spring.application.name}")
    private String keyBase;    //key基础 判断程序

    private LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);

    private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    private static <T> Schema<T> getSchema(Class<T> clazz) {
        Schema<T> schema = (Schema<T>) cachedSchema.get(clazz);
        if (schema == null) {
            schema = RuntimeSchema.createFrom(clazz);
            cachedSchema.put(clazz, schema);
        }
        return schema;
    }

    @Bean
    public RedisDao redisFactory() {
        JedisPoolConfig config = new JedisPoolConfig();
        //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
        //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
        config.setMaxTotal(maxTotal);
        //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
        config.setMaxIdle(maxIdle);
        //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
        config.setMaxWaitMillis(waitMill);
        //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
        config.setTestOnBorrow(true);

        if (StringUtils.isNotBlank(password)) {
            jedisPool = new JedisPool(config, ip, port, 3000, password);
        } else {
            jedisPool = new JedisPool(config, ip, port);
        }
        return new RedisDao();
    }

    public <T> T getBean(String key, Class<T> targetClass) {
        try {
            key = keyBase + ":" + key;
            try (Jedis jedis = getJedis()) {
                byte[] bytes = jedis.get(key.getBytes());
                if (bytes != null) {
                    Schema<T> schema = getSchema(targetClass);
                    T result = targetClass.newInstance();
                    ProtobufIOUtil.mergeFrom(bytes, result, schema);
                    return result;
                }
            }
        } catch (Exception e) {
            log.error("jedis", e);
        }
        return null;
    }

    public <T> String putBean(String key, T object, Class<T> clazz) {
        return putBean(key, object, clazz, 60 * 60);
    }

    public <T> String putBean(String key, T object, Class<T> clazz, Integer timeout) {
        try {
            key = keyBase + ":" + key;
            try (Jedis jedis = getJedis()) {
                Schema<T> schema = getSchema(clazz);
                byte[] bytes = ProtobufIOUtil.toByteArray(object, schema, buffer);
//                String bytes = JSON.toJSONString(object);
                if (timeout == null) {
                    //超时缓存
                    timeout = 60 * 60;  //一个小时
                }
                return jedis.setex(key.getBytes(), timeout, bytes);
            }
        } catch (Exception e) {
            log.error("jedis", e);
        }

        return null;
    }


    public List getList(String key) {
        try {
            key = keyBase + key;
            try (Jedis jedis = getJedis()) {
                byte[] bytes = jedis.get(key.getBytes());
//                String bytes =  jedis.get(key);
                if (bytes != null) {
                    if ("[]".equals(bytes)) {
                        return new ArrayList();
                    }
                    Schema<List> schema = getSchema(List.class);
                    List result = new ArrayList();
                    ProtobufIOUtil.mergeFrom(bytes, result, schema);
//                    List result = (List) JSON.parseObject(bytes,cl);
                    return result;
                }
            }
        } catch (Exception e) {
            log.error("jedis", e);
        }
        return null;
    }

    public <T> String putList(String key, List list) {
        return putList(key, list, 60 * 60);
    }


    public String putList(String key, List list, Integer timeout) {
        try {
            key = keyBase + ":" + key;
            try (Jedis jedis = getJedis()) {
                Schema<List> schema = getSchema(List.class);
                byte[] bytes = ProtobufIOUtil.toByteArray(list, schema, buffer);
                if (timeout == null) {
                    //超时缓存
                    timeout = 60 * 60;  //一个小时
                }
                return jedis.setex(key.getBytes(), timeout, bytes);
            }
        } catch (Exception e) {
            log.error("jedis", e);
        }

        return null;
    }

    public void delete(String key) {
        key = keyBase + key;
        Jedis jedis = getJedis();
        jedis.del(key);  //删除某个键
    }


    /**
     * 获取Jedis实例
     *
     * @return
     */
    private Jedis getJedis() {
        if (jedisPool != null) {
            return jedisPool.getResource();
        } else {
            return null;
        }
    }
}
