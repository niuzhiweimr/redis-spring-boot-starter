package com.easy.redis.annotation;

import com.easy.redis.adapter.RedisAdapter;
import com.easy.redis.core.RedisApplicationContext;
import com.easy.redis.core.RedisEnvironment;
import com.easy.redis.handler.RedisClusterProcessor;
import com.easy.redis.handler.RedisSentinelProcessor;
import com.easy.redis.handler.RedisSingleProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * redis 配置初始化
 *
 * @author niuzhiwei
 */
public class RedisConfigRegistrar implements ImportBeanDefinitionRegistrar {


    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {

        AnnotationAttributes attributes = AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(
                EnableRedis.class.getName()));

        String redisMode = attributes.getString("value");

        RedisAdapter redisAdapter = new RedisAdapter();
        redisAdapter.setRedisMode(redisMode);
        RedisSingleProcessor redisSingleProcessor = new RedisSingleProcessor();
        RedisClusterProcessor redisClusterProcessor = new RedisClusterProcessor();
        RedisSentinelProcessor redisSentinelProcessor = new RedisSentinelProcessor();

        RedisEnvironment redisEnvironment = new RedisEnvironment();
        redisEnvironment.setRedisConnectionFactoryIsInit(Boolean.TRUE);

        RedisApplicationContext
                .builder()
                .build().setBean(redisEnvironment.getClass().getName(), redisEnvironment)
                .setBean(redisAdapter.getClass().getName(), redisAdapter)
                .setBean(redisSingleProcessor.getClass().getName(), redisSingleProcessor)
                .setBean(redisClusterProcessor.getClass().getName(), redisClusterProcessor)
                .setBean(redisSentinelProcessor.getClass().getName(), redisSentinelProcessor);

    }


}
