package com.easy.redis.handler;

import com.easy.redis.boot.RedisBeanContext;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.Jedis;

/**
 * redis 哨兵处理类
 * <p>
 * TODO:待实现
 *
 * @author niuzhiwei
 */
public class RedisSentinelProcessor extends AbstractRedisProcessor {

    private Jedis getJedisSentinel() {
        return null;
    }


    @Override
    public JedisConnectionFactory getConnectionFactory() {
        return RedisBeanContext.getBean(JedisConnectionFactory.class);
    }

    @Override
    public void close() {

    }
}
