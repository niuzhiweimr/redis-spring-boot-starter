package com.easy.redis.boot;

import com.easy.redis.properties.RedisProperties;
import com.easy.redis.template.EasyRedisTemplate;
import com.easy.redis.util.RedisConnectionFactoryUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import javax.annotation.Resource;

/**
 * 操作bean类初始化
 *
 * @author niuzhiwei
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisInitialization {

    @Resource
    private RedisProperties redisProperties;

    @Bean
    @ConditionalOnMissingBean(EasyRedisTemplate.class)
    public EasyRedisTemplate easyRedisTemplate() {

        return new EasyRedisTemplate();
    }

    @Bean
    @ConditionalOnMissingBean(JedisConnectionFactory.class)
    public JedisConnectionFactory jedisConnectionFactory() {

        return RedisConnectionFactoryUtil.init(redisProperties);
    }


}

