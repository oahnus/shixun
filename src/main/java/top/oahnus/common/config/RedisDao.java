package top.oahnus.common.config;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by oahnus on 2017/10/3
 * 8:39.
 */
@Configuration
@Slf4j
public class RedisDao {
    @Value("${spring.application.name}")
    private String keyBase;    //key基础 判断程序

    private Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<>();

    @Autowired
    private StringRedisTemplate redisTemplate;

    @SuppressWarnings("unchecked")
    private <T> Schema<T> getSchema(Class<T> clazz) {
        Schema<T> schema = (Schema<T>) cachedSchema.get(clazz);
        if (schema == null) {
            schema = RuntimeSchema.createFrom(clazz);
            cachedSchema.put(clazz, schema);
        }
        return schema;
    }

    public <T> T getBean(String key, Class<T> targetClass) {
        try {
            key = keyBase + ":" + key;
            String val = redisTemplate.opsForValue().get(key);
            Schema<T> schema = getSchema(targetClass);
            T result = targetClass.newInstance();
            ProtobufIOUtil.mergeFrom(val.getBytes(), result, schema);
            return result;
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> void putBean(String key, T object, Class<T> clazz) {
        putBean(key, object, clazz, 60 * 60);
    }

    public <T> void putBean(String key, T object, Class<T> clazz, Integer timeout) {
        key = keyBase + ":" + key;
        Schema<T> schema = getSchema(clazz);
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        byte[] bytes = ProtobufIOUtil.toByteArray(object, schema, buffer);
        if (timeout == null) {
            //超时缓存
            timeout = 60 * 60;  //一个小时
        }
        redisTemplate.opsForValue().set(key, new String(bytes), timeout, TimeUnit.SECONDS);
    }
}
