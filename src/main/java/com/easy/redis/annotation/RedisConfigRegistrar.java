package com.easy.redis.annotation;

import com.easy.redis.adapter.RedisAdapter;
import com.easy.redis.cache.RedisAdapterAndProcessorCache;
import com.easy.redis.handler.RedisClusterProcessor;
import com.easy.redis.handler.RedisSentinelProcessor;
import com.easy.redis.handler.RedisSingleProcessor;
import com.easy.redis.util.RedisConnectionFactoryUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * redis 配置初始化
 *
 * @author niuzhiwei
 */
@Slf4j
public class RedisConfigRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {

        AnnotationAttributes attributes = AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(
                EnableRedis.class.getName()));

        String redisMode = attributes.getString("value");

        RedisAdapter redisAdapter = new RedisAdapter();
        redisAdapter.setRedisMode(redisMode);
        RedisAdapterAndProcessorCache.RedisAdapterMap.put(
                RedisAdapterAndProcessorCache.ADAPTER_KEY, redisAdapter);

        RedisConnectionFactoryUtil.isInit = true;

        RedisSingleProcessor redisSingleProcessor = new RedisSingleProcessor();
        RedisClusterProcessor redisClusterProcessor = new RedisClusterProcessor();
        RedisSentinelProcessor redisSentinelProcessor = new RedisSentinelProcessor();
        RedisAdapterAndProcessorCache.RedisProcessorMap.put(
                RedisAdapterAndProcessorCache.PROCESSOR_SINGLE_KEY, redisSingleProcessor);
        RedisAdapterAndProcessorCache.RedisProcessorMap.put(
                RedisAdapterAndProcessorCache.PROCESSOR_CLUSTER_KEY, redisClusterProcessor);
        RedisAdapterAndProcessorCache.RedisProcessorMap.put(
                RedisAdapterAndProcessorCache.PROCESSOR_SENTINEL_KEY, redisSentinelProcessor);

    }


}
