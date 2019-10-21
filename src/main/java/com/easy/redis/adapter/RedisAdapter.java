package com.easy.redis.adapter;

import com.easy.redis.base.AbstractRedisProcessor;
import com.easy.redis.constants.RedisModeConstants;
import com.easy.redis.core.RedisApplicationContext;
import com.easy.redis.handler.RedisClusterProcessor;
import com.easy.redis.handler.RedisSentinelProcessor;
import com.easy.redis.handler.RedisSingleProcessor;
import lombok.extern.slf4j.Slf4j;

/**
 * redis适配器，适配redis各模式处理类
 *
 * @author niuzhiwei
 */
@Slf4j
public class RedisAdapter {

	private String redisMode;


	public RedisAdapter() {
	}

	public String getRedisMode() {
		return redisMode;
	}

	public void setRedisMode(String redisMode) {
		this.redisMode = redisMode;
	}

	/**
	 * 获取redis 处理器
	 *
	 * @return AbstractRedisProcessor
	 */
	public AbstractRedisProcessor getRedisProcessor() {

		switch (redisMode) {
			case RedisModeConstants.REDIS_SINGLE:
				return RedisApplicationContext
						.builder()
						.build()
						.getBean(RedisSingleProcessor.class);
			case RedisModeConstants.REDIS_CLUSTER:
				return RedisApplicationContext
						.builder()
						.build()
						.getBean(RedisClusterProcessor.class);
			case RedisModeConstants.REDIS_SENTINEL:
				return RedisApplicationContext
						.builder()
						.build()
						.getBean(RedisSentinelProcessor.class);
			default:
				log.error("Failed to obtain redis schema instance params {}", redisMode);
				throw new RuntimeException("Failed to obtain redis schema instance");
		}
	}
}
