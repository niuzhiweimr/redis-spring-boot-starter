package com.easy.redis.core;

import lombok.Builder;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * etcRedis容器核心上下文
 *
 * @author niuzhiwei
 */
@Builder
public class RedisApplicationContext {

    /**
     * 上下文容器
     */
    private final static Map<String, Object> CONTAINER = new ConcurrentHashMap<>();

    /**
     * 获取环境对象
     *
     * @return
     */
    public RedisEnvironment getEnv() {
        return CONTAINER.get(RedisEnvironment.class.getName()) == null ?
                new RedisEnvironment() : (RedisEnvironment) CONTAINER.get(RedisEnvironment.class.getName());
    }

    public Object getBean(String className) {
        if (StringUtils.isEmpty(className)) {
            return null;
        }
        return CONTAINER.get(className);
    }

    public <T> T getBean(Class<T> clazz) {
        if (Objects.isNull(clazz)) {
            return null;
        }
        return (T) CONTAINER.get(clazz.getName());
    }

    public boolean containsBean(String beanName) {
        return CONTAINER.containsKey(beanName);
    }

    public boolean containsBean(Class clazz) {
        return CONTAINER.containsKey(clazz.getName());
    }

    public RedisApplicationContext setBean(String className, Object obj) {
        CONTAINER.put(className, obj);
        return new RedisApplicationContext();
    }

    public RedisApplicationContext setBean(Class clazz, Object obj) {
        CONTAINER.put(clazz.getName(), obj);
        return new RedisApplicationContext();
    }


}
