package com.easy.redis.adapter;

import com.easy.redis.constants.RedisModeConstants;
import com.easy.redis.cache.RedisAdapterAndProcessorCache;
import com.easy.redis.handler.RedisClusterProcessor;
import com.easy.redis.handler.RedisSingleProcessor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * redis适配器，适配redis各模式处理类
 *
 * @author niuzhiwei
 */
@Slf4j
@SuppressWarnings("Duplicates")
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
	 * 判断key是否存在
	 */
	public boolean exists(String key) {

		switch (redisMode) {
		case RedisModeConstants.REDIS_SINGLE:
			return ((RedisSingleProcessor) getRedisProcessor()).exists(key);
		case RedisModeConstants.REDIS_CLUSTER:
			return ((RedisClusterProcessor) getRedisProcessor()).exists(key);
		case RedisModeConstants.REDIS_SENTINEL:
			log.error("This operation is not implemented in the current mode , mode {}", redisMode);
			throw new RuntimeException("This operation is not implemented in the current mode");
		default:
			log.error("Failed to obtain redis schema instance params {}", redisMode);
			throw new RuntimeException("Failed to obtain redis schema instance");
		}

	}

	/**
	 * 获取key值对应value长度
	 *
	 * @param key
	 * @return
	 */
	public Long length(String key) {

		switch (redisMode) {
		case RedisModeConstants.REDIS_SINGLE:
			return ((RedisSingleProcessor) getRedisProcessor()).length(key);
		case RedisModeConstants.REDIS_CLUSTER:
			return ((RedisClusterProcessor) getRedisProcessor()).length(key);
		case RedisModeConstants.REDIS_SENTINEL:
			log.error("This operation is not implemented in the current mode , mode {}", redisMode);
			throw new RuntimeException("This operation is not implemented in the current mode");
		default:
			log.error("Failed to obtain redis schema instance params {}", redisMode);
			throw new RuntimeException("Failed to obtain redis schema instance");
		}
	}

	/**
	 * 新增或修改
	 */
	public void set(String key, Object obj) {

		switch (redisMode) {
		case RedisModeConstants.REDIS_SINGLE:
			((RedisSingleProcessor) getRedisProcessor()).set(key, obj);
			break;
		case RedisModeConstants.REDIS_CLUSTER:
			((RedisClusterProcessor) getRedisProcessor()).set(key, obj);
			break;
		case RedisModeConstants.REDIS_SENTINEL:
			log.error("This operation is not implemented in the current mode , mode {}", redisMode);
			throw new RuntimeException("This operation is not implemented in the current mode");
		default:
			log.error("Failed to obtain redis schema instance params {}", redisMode);
			throw new RuntimeException("Failed to obtain redis schema instance");
		}
	}

	/**
	 * 新增或修改
	 */
	public void set(String key, String value) {

		switch (redisMode) {
		case RedisModeConstants.REDIS_SINGLE:
			((RedisSingleProcessor) getRedisProcessor()).set(key, value);
			break;
		case RedisModeConstants.REDIS_CLUSTER:
			((RedisClusterProcessor) getRedisProcessor()).set(key, value);
			break;
		case RedisModeConstants.REDIS_SENTINEL:
			log.error("This operation is not implemented in the current mode , mode {}", redisMode);
			throw new RuntimeException("This operation is not implemented in the current mode");
		default:
			log.error("Failed to obtain redis schema instance params {}", redisMode);
			throw new RuntimeException("Failed to obtain redis schema instance");
		}
	}

	/**
	 * 新增或修改
	 *
	 * @param key
	 * @param value
	 * @param seconds
	 *            有效期（单位：s）--正数
	 */
	public void setex(String key, String value, int seconds) {

		switch (redisMode) {
		case RedisModeConstants.REDIS_SINGLE:
			((RedisSingleProcessor) getRedisProcessor()).setex(key, value, seconds);
			break;
		case RedisModeConstants.REDIS_CLUSTER:
			((RedisClusterProcessor) getRedisProcessor()).setex(key, value, seconds);
			break;
		case RedisModeConstants.REDIS_SENTINEL:
			log.error("This operation is not implemented in the current mode , mode {}", redisMode);
			throw new RuntimeException("This operation is not implemented in the current mode");
		default:
			log.error("Failed to obtain redis schema instance params {}", redisMode);
			throw new RuntimeException("Failed to obtain redis schema instance");
		}
	}

	/**
	 * 新增或修改
	 *
	 * @param key
	 * @param obj
	 * @param seconds
	 *            有效期（单位：s）
	 */
	public void setex(String key, Object obj, int seconds) {

		switch (redisMode) {
		case RedisModeConstants.REDIS_SINGLE:
			((RedisSingleProcessor) getRedisProcessor()).setex(key, obj, seconds);
			break;
		case RedisModeConstants.REDIS_CLUSTER:
			((RedisClusterProcessor) getRedisProcessor()).setex(key, obj, seconds);
			break;
		case RedisModeConstants.REDIS_SENTINEL:
			log.error("This operation is not implemented in the current mode , mode {}", redisMode);
			throw new RuntimeException("This operation is not implemented in the current mode");
		default:
			log.error("Failed to obtain redis schema instance params {}", redisMode);
			throw new RuntimeException("Failed to obtain redis schema instance");
		}
	}

	/**
	 * 获取key的值
	 *
	 * @return 字符串
	 */
	public String get(String key) {

		switch (redisMode) {
		case RedisModeConstants.REDIS_SINGLE:
			return ((RedisSingleProcessor) getRedisProcessor()).get(key);
		case RedisModeConstants.REDIS_CLUSTER:
			return ((RedisClusterProcessor) getRedisProcessor()).get(key);
		case RedisModeConstants.REDIS_SENTINEL:
			log.error("This operation is not implemented in the current mode , mode {}", redisMode);
			throw new RuntimeException("This operation is not implemented in the current mode");
		default:
			log.error("Failed to obtain redis schema instance params {}", redisMode);
			throw new RuntimeException("Failed to obtain redis schema instance");
		}
	}

	/**
	 * 获取key的值
	 *
	 * @param keys
	 *            多个key值，示例：(key1, key2, key3...)
	 * @return List集合
	 */
	public List<String> get(String... keys) {

		switch (redisMode) {
		case RedisModeConstants.REDIS_SINGLE:
			return ((RedisSingleProcessor) getRedisProcessor()).get(keys);
		case RedisModeConstants.REDIS_CLUSTER:
			return ((RedisClusterProcessor) getRedisProcessor()).get(keys);
		case RedisModeConstants.REDIS_SENTINEL:
			log.error("This operation is not implemented in the current mode , mode {}", redisMode);
			throw new RuntimeException("This operation is not implemented in the current mode");
		default:
			log.error("Failed to obtain redis schema instance params {}", redisMode);
			throw new RuntimeException("Failed to obtain redis schema instance");
		}
	}

	/**
	 * 获取key的值
	 *
	 * @return 对象
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(String key, Class<T> clazz) {

		switch (redisMode) {
		case RedisModeConstants.REDIS_SINGLE:
			return ((RedisSingleProcessor) getRedisProcessor()).get(key, clazz);
		case RedisModeConstants.REDIS_CLUSTER:
			return ((RedisClusterProcessor) getRedisProcessor()).get(key, clazz);
		case RedisModeConstants.REDIS_SENTINEL:
			log.error("This operation is not implemented in the current mode , mode {}", redisMode);
			throw new RuntimeException("This operation is not implemented in the current mode");
		default:
			log.error("Failed to obtain redis schema instance params {}", redisMode);
			throw new RuntimeException("Failed to obtain redis schema instance");
		}
	}

	/**
	 * 获取key的有效期
	 */
	public Long getValid(String key) {

		switch (redisMode) {
		case RedisModeConstants.REDIS_SINGLE:
			return ((RedisSingleProcessor) getRedisProcessor()).getValid(key);
		case RedisModeConstants.REDIS_CLUSTER:
			return ((RedisClusterProcessor) getRedisProcessor()).getValid(key);
		case RedisModeConstants.REDIS_SENTINEL:
			log.error("This operation is not implemented in the current mode , mode {}", redisMode);
			throw new RuntimeException("This operation is not implemented in the current mode");
		default:
			log.error("Failed to obtain redis schema instance params {}", redisMode);
			throw new RuntimeException("Failed to obtain redis schema instance");
		}

	}

	/**
	 * 移除key的有效期
	 */
	public Long removeValid(String key) {

		switch (redisMode) {
		case RedisModeConstants.REDIS_SINGLE:
			return ((RedisSingleProcessor) getRedisProcessor()).removeValid(key);
		case RedisModeConstants.REDIS_CLUSTER:
			return ((RedisClusterProcessor) getRedisProcessor()).removeValid(key);
		case RedisModeConstants.REDIS_SENTINEL:
			log.error("This operation is not implemented in the current mode , mode {}", redisMode);
			throw new RuntimeException("This operation is not implemented in the current mode");
		default:
			log.error("Failed to obtain redis schema instance params {}", redisMode);
			throw new RuntimeException("Failed to obtain redis schema instance");
		}

	}

	/**
	 * 获取Key的生存时间
	 *
	 * @param key
	 * @return
	 */
	public Long getTimeOut(String key) {

		switch (redisMode) {
		case RedisModeConstants.REDIS_SINGLE:
			return ((RedisSingleProcessor) getRedisProcessor()).getTimeOut(key);
		case RedisModeConstants.REDIS_CLUSTER:
			return ((RedisClusterProcessor) getRedisProcessor()).getTimeOut(key);
		case RedisModeConstants.REDIS_SENTINEL:
			log.error("This operation is not implemented in the current mode , mode {}", redisMode);
			throw new RuntimeException("This operation is not implemented in the current mode");
		default:
			log.error("Failed to obtain redis schema instance params {}", redisMode);
			throw new RuntimeException("Failed to obtain redis schema instance");
		}
	}

	/**
	 * 删除key
	 */
	public Long delete(String key) {

		switch (redisMode) {
		case RedisModeConstants.REDIS_SINGLE:
			return ((RedisSingleProcessor) getRedisProcessor()).delete(key);
		case RedisModeConstants.REDIS_CLUSTER:
			return ((RedisClusterProcessor) getRedisProcessor()).delete(key);
		case RedisModeConstants.REDIS_SENTINEL:
			log.error("This operation is not implemented in the current mode , mode {}", redisMode);
			throw new RuntimeException("This operation is not implemented in the current mode");
		default:
			log.error("Failed to obtain redis schema instance params {}", redisMode);
			throw new RuntimeException("Failed to obtain redis schema instance");
		}
	}

	/**
	 * key值自增1
	 *
	 * @param key
	 * @return 自增以后的key
	 */
	public Long incr(String key) {

		switch (redisMode) {
		case RedisModeConstants.REDIS_SINGLE:
			return ((RedisSingleProcessor) getRedisProcessor()).incr(key);
		case RedisModeConstants.REDIS_CLUSTER:
			return ((RedisClusterProcessor) getRedisProcessor()).incr(key);
		case RedisModeConstants.REDIS_SENTINEL:
			log.error("This operation is not implemented in the current mode , mode {}", redisMode);
			throw new RuntimeException("This operation is not implemented in the current mode");
		default:
			log.error("Failed to obtain redis schema instance params {}", redisMode);
			throw new RuntimeException("Failed to obtain redis schema instance");
		}

	}

	/**
	 * hash存储
	 *
	 * @param key
	 * @param field
	 *            属性
	 * @param value
	 *            值
	 */
	public void hset(String key, String field, String value) {

		switch (redisMode) {
		case RedisModeConstants.REDIS_SINGLE:
			((RedisSingleProcessor) getRedisProcessor()).hset(key, field, value);
			break;
		case RedisModeConstants.REDIS_CLUSTER:
			((RedisClusterProcessor) getRedisProcessor()).hset(key, field, value);
			break;
		case RedisModeConstants.REDIS_SENTINEL:
			log.error("This operation is not implemented in the current mode , mode {}", redisMode);
			throw new RuntimeException("This operation is not implemented in the current mode");
		default:
			log.error("Failed to obtain redis schema instance params {}", redisMode);
			throw new RuntimeException("Failed to obtain redis schema instance");
		}
	}

	/**
	 * hash获取值
	 *
	 * @param key
	 * @param field
	 *            属性
	 * @return
	 */
	public String hget(String key, String field) {

		switch (redisMode) {
		case RedisModeConstants.REDIS_SINGLE:
			return ((RedisSingleProcessor) getRedisProcessor()).hget(key, field);
		case RedisModeConstants.REDIS_CLUSTER:
			return ((RedisClusterProcessor) getRedisProcessor()).hget(key, field);
		case RedisModeConstants.REDIS_SENTINEL:
			log.error("This operation is not implemented in the current mode , mode {}", redisMode);
			throw new RuntimeException("This operation is not implemented in the current mode");
		default:
			log.error("Failed to obtain redis schema instance params {}", redisMode);
			throw new RuntimeException("Failed to obtain redis schema instance");
		}

	}

	/**
	 * hash存储多个
	 *
	 * @param key
	 * @param map
	 */
	public void hmset(String key, Map<String, String> map) {

		switch (redisMode) {
		case RedisModeConstants.REDIS_SINGLE:
			((RedisSingleProcessor) getRedisProcessor()).hmset(key, map);
			break;
		case RedisModeConstants.REDIS_CLUSTER:
			((RedisClusterProcessor) getRedisProcessor()).hmset(key, map);
			break;
		case RedisModeConstants.REDIS_SENTINEL:
			log.error("This operation is not implemented in the current mode , mode {}", redisMode);
			throw new RuntimeException("This operation is not implemented in the current mode");
		default:
			log.error("Failed to obtain redis schema instance params {}", redisMode);
			throw new RuntimeException("Failed to obtain redis schema instance");
		}

	}

	/**
	 * hash获取多个
	 *
	 * @param key
	 * @param fields
	 * @return
	 */
	public List<String> hmget(String key, String... fields) {

		switch (redisMode) {
		case RedisModeConstants.REDIS_SINGLE:
			return ((RedisSingleProcessor) getRedisProcessor()).hmget(key, fields);
		case RedisModeConstants.REDIS_CLUSTER:
			return ((RedisClusterProcessor) getRedisProcessor()).hmget(key, fields);
		case RedisModeConstants.REDIS_SENTINEL:
			log.error("This operation is not implemented in the current mode , mode {}", redisMode);
			throw new RuntimeException("This operation is not implemented in the current mode");
		default:
			log.error("Failed to obtain redis schema instance params {}", redisMode);
			throw new RuntimeException("Failed to obtain redis schema instance");
		}
	}

	/**
	 * hash获取属性个数
	 *
	 * @param key
	 * @return
	 */
	public Long hlen(String key) {

		switch (redisMode) {
		case RedisModeConstants.REDIS_SINGLE:
			return ((RedisSingleProcessor) getRedisProcessor()).hlen(key);
		case RedisModeConstants.REDIS_CLUSTER:
			return ((RedisClusterProcessor) getRedisProcessor()).hlen(key);
		case RedisModeConstants.REDIS_SENTINEL:
			log.error("This operation is not implemented in the current mode , mode {}", redisMode);
			throw new RuntimeException("This operation is not implemented in the current mode");
		default:
			log.error("Failed to obtain redis schema instance params {}", redisMode);
			throw new RuntimeException("Failed to obtain redis schema instance");
		}

	}

	/**
	 * hash获取全部
	 *
	 * @param key
	 * @return
	 */
	public Map<String, String> hgetAll(String key) {

		switch (redisMode) {
		case RedisModeConstants.REDIS_SINGLE:
			return ((RedisSingleProcessor) getRedisProcessor()).hgetAll(key);
		case RedisModeConstants.REDIS_CLUSTER:
			return ((RedisClusterProcessor) getRedisProcessor()).hgetAll(key);
		case RedisModeConstants.REDIS_SENTINEL:
			log.error("This operation is not implemented in the current mode , mode {}", redisMode);
			throw new RuntimeException("This operation is not implemented in the current mode");
		default:
			log.error("Failed to obtain redis schema instance params {}", redisMode);
			throw new RuntimeException("Failed to obtain redis schema instance");
		}
	}

	/**
	 * list-栈 存储
	 *
	 * @param key
	 * @param strings
	 */
	public void lpush(String key, String... strings) {

		switch (redisMode) {
		case RedisModeConstants.REDIS_SINGLE:
			((RedisSingleProcessor) getRedisProcessor()).lpush(key, strings);
			break;
		case RedisModeConstants.REDIS_CLUSTER:
			((RedisClusterProcessor) getRedisProcessor()).lpush(key, strings);
			break;
		case RedisModeConstants.REDIS_SENTINEL:
			log.error("This operation is not implemented in the current mode , mode {}", redisMode);
			throw new RuntimeException("This operation is not implemented in the current mode");
		default:
			log.error("Failed to obtain redis schema instance params {}", redisMode);
			throw new RuntimeException("Failed to obtain redis schema instance");
		}
	}

	/**
	 * list-队列 存储
	 *
	 * @param key
	 * @param strings
	 */
	public void rpush(String key, String... strings) {

		switch (redisMode) {
		case RedisModeConstants.REDIS_SINGLE:
			((RedisSingleProcessor) getRedisProcessor()).rpush(key, strings);
			break;
		case RedisModeConstants.REDIS_CLUSTER:
			((RedisClusterProcessor) getRedisProcessor()).rpush(key, strings);
			break;
		case RedisModeConstants.REDIS_SENTINEL:
			log.error("This operation is not implemented in the current mode , mode {}", redisMode);
			throw new RuntimeException("This operation is not implemented in the current mode");
		default:
			log.error("Failed to obtain redis schema instance params {}", redisMode);
			throw new RuntimeException("Failed to obtain redis schema instance");
		}
	}

	/**
	 * list-获取长度
	 *
	 * @param key
	 * @return
	 */
	public Long llen(String key) {

		switch (redisMode) {
		case RedisModeConstants.REDIS_SINGLE:
			return ((RedisSingleProcessor) getRedisProcessor()).llen(key);
		case RedisModeConstants.REDIS_CLUSTER:
			return ((RedisClusterProcessor) getRedisProcessor()).llen(key);
		case RedisModeConstants.REDIS_SENTINEL:
			log.error("This operation is not implemented in the current mode , mode {}", redisMode);
			throw new RuntimeException("This operation is not implemented in the current mode");
		default:
			log.error("Failed to obtain redis schema instance params {}", redisMode);
			throw new RuntimeException("Failed to obtain redis schema instance");
		}
	}

	/**
	 * 返回index处的元素
	 *
	 * @param key
	 * @param index
	 * @return
	 */
	public String lindex(String key, long index) {

		switch (redisMode) {
		case RedisModeConstants.REDIS_SINGLE:
			return ((RedisSingleProcessor) getRedisProcessor()).lindex(key, index);
		case RedisModeConstants.REDIS_CLUSTER:
			return ((RedisClusterProcessor) getRedisProcessor()).lindex(key, index);
		case RedisModeConstants.REDIS_SENTINEL:
			log.error("This operation is not implemented in the current mode , mode {}", redisMode);
			throw new RuntimeException("This operation is not implemented in the current mode");
		default:
			log.error("Failed to obtain redis schema instance params {}", redisMode);
			throw new RuntimeException("Failed to obtain redis schema instance");
		}
	}

	/**
	 * list-获取所有元素
	 *
	 * @param key
	 * @return
	 */
	public List<String> lrangeAll(String key) {

		switch (redisMode) {
		case RedisModeConstants.REDIS_SINGLE:
			return ((RedisSingleProcessor) getRedisProcessor()).lrangeAll(key);
		case RedisModeConstants.REDIS_CLUSTER:
			return ((RedisClusterProcessor) getRedisProcessor()).lrangeAll(key);
		case RedisModeConstants.REDIS_SENTINEL:
			log.error("This operation is not implemented in the current mode , mode {}", redisMode);
			throw new RuntimeException("This operation is not implemented in the current mode");
		default:
			log.error("Failed to obtain redis schema instance params {}", redisMode);
			throw new RuntimeException("Failed to obtain redis schema instance");
		}
	}

	/**
	 * list-获取指定区间元素
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<String> lrange(String key, long start, long end) {

		switch (redisMode) {
		case RedisModeConstants.REDIS_SINGLE:
			return ((RedisSingleProcessor) getRedisProcessor()).lrange(key, start, end);
		case RedisModeConstants.REDIS_CLUSTER:
			return ((RedisClusterProcessor) getRedisProcessor()).lrange(key, start, end);
		case RedisModeConstants.REDIS_SENTINEL:
			log.error("This operation is not implemented in the current mode , mode {}", redisMode);
			throw new RuntimeException("This operation is not implemented in the current mode");
		default:
			log.error("Failed to obtain redis schema instance params {}", redisMode);
			throw new RuntimeException("Failed to obtain redis schema instance");
		}
	}

	/**
	 * list-移除元素（出栈）
	 *
	 * @param key
	 * @return
	 */
	public String lpop(String key) {

		switch (redisMode) {
		case RedisModeConstants.REDIS_SINGLE:
			return ((RedisSingleProcessor) getRedisProcessor()).lpop(key);
		case RedisModeConstants.REDIS_CLUSTER:
			return ((RedisClusterProcessor) getRedisProcessor()).lpop(key);
		case RedisModeConstants.REDIS_SENTINEL:
			log.error("This operation is not implemented in the current mode , mode {}", redisMode);
			throw new RuntimeException("This operation is not implemented in the current mode");
		default:
			log.error("Failed to obtain redis schema instance params {}", redisMode);
			throw new RuntimeException("Failed to obtain redis schema instance");
		}
	}

	/**
	 * list-移除元素（出队列）
	 *
	 * @param key
	 * @return
	 */
	public String rpop(String key) {

		switch (redisMode) {
		case RedisModeConstants.REDIS_SINGLE:
			return ((RedisSingleProcessor) getRedisProcessor()).rpop(key);
		case RedisModeConstants.REDIS_CLUSTER:
			return ((RedisClusterProcessor) getRedisProcessor()).rpop(key);
		case RedisModeConstants.REDIS_SENTINEL:
			log.error("This operation is not implemented in the current mode , mode {}", redisMode);
			throw new RuntimeException("This operation is not implemented in the current mode");
		default:
			log.error("Failed to obtain redis schema instance params {}", redisMode);
			throw new RuntimeException("Failed to obtain redis schema instance");
		}
	}

	/**
	 * 设置key的过期时间
	 *
	 * @param key
	 * @param seconds
	 */
	public void setTimeOut(String key, int seconds) {

		switch (redisMode) {
		case RedisModeConstants.REDIS_SINGLE:
			((RedisSingleProcessor) getRedisProcessor()).setTimeOut(key, seconds);
			break;
		case RedisModeConstants.REDIS_CLUSTER:
			((RedisClusterProcessor) getRedisProcessor()).setTimeOut(key, seconds);
			break;
		case RedisModeConstants.REDIS_SENTINEL:
			log.error("This operation is not implemented in the current mode , mode {}", redisMode);
			throw new RuntimeException("This operation is not implemented in the current mode");
		default:
			log.error("Failed to obtain redis schema instance params {}", redisMode);
			throw new RuntimeException("Failed to obtain redis schema instance");
		}
	}

	/**
	 * 新增或修改-key存在，不存储；反之 注： setnx(key,
	 * value)方法在set字段时会先判断key是否存在，key值存在-不做任何操作并返回0；key值不存在-存储key并返回1
	 * 但是setnx存储key-value的同时不能设置有效期，所以需要用expire()手动设置有效期 --> expire(key,
	 * seconds):设置有效期
	 *
	 * @param key
	 * @param value
	 * @param seconds
	 *            有效期（单位：s）
	 */
	@Deprecated
	public void setnx(String key, String value, int seconds) {

		switch (redisMode) {
		case RedisModeConstants.REDIS_SINGLE:
			((RedisSingleProcessor) getRedisProcessor()).setnx(key, value, seconds);
			break;
		case RedisModeConstants.REDIS_CLUSTER:
			((RedisClusterProcessor) getRedisProcessor()).setnx(key, value, seconds);
			break;
		case RedisModeConstants.REDIS_SENTINEL:
			log.error("This operation is not implemented in the current mode , mode {}", redisMode);
			throw new RuntimeException("This operation is not implemented in the current mode");
		default:
			log.error("Failed to obtain redis schema instance params {}", redisMode);
			throw new RuntimeException("Failed to obtain redis schema instance");
		}
	}

	@Deprecated
	public int setnxAndReturnSuccess(String key, String value, int seconds) {

		switch (redisMode) {
		case RedisModeConstants.REDIS_SINGLE:
			return ((RedisSingleProcessor) getRedisProcessor()).setnxAndReturnSuccess(key, value, seconds);
		case RedisModeConstants.REDIS_CLUSTER:
			return ((RedisClusterProcessor) getRedisProcessor()).setnxAndReturnSuccess(key, value, seconds);
		case RedisModeConstants.REDIS_SENTINEL:
			log.error("This operation is not implemented in the current mode , mode {}", redisMode);
			throw new RuntimeException("This operation is not implemented in the current mode");
		default:
			log.error("Failed to obtain redis schema instance params {}", redisMode);
			throw new RuntimeException("Failed to obtain redis schema instance");
		}
	}

	/**
	 * 新增或修改-key存在，不存储；反之
	 *
	 * @param key
	 * @param obj
	 * @param seconds
	 *            有效期（单位：s）
	 */
	@Deprecated
	public void setnx(String key, Object obj, int seconds) {

		switch (redisMode) {
		case RedisModeConstants.REDIS_SINGLE:
			((RedisSingleProcessor) getRedisProcessor()).setnx(key, obj, seconds);
			break;
		case RedisModeConstants.REDIS_CLUSTER:
			((RedisClusterProcessor) getRedisProcessor()).setnx(key, obj, seconds);
			break;
		case RedisModeConstants.REDIS_SENTINEL:
			log.error("This operation is not implemented in the current mode , mode {}", redisMode);
			throw new RuntimeException("This operation is not implemented in the current mode");
		default:
			log.error("Failed to obtain redis schema instance params {}", redisMode);
			throw new RuntimeException("Failed to obtain redis schema instance");
		}
	}

	/**
	 * redis分布式锁-锁定
	 *
	 * @param key
	 *            分布式锁-key
	 * @param value
	 *            分布式锁-value
	 * @param expire
	 *            有效期（单位：ms）
	 * @return true-获取锁成功/false-获取锁失败
	 */
	public boolean tryLock(String key, String value, long expire) {

		switch (redisMode) {
		case RedisModeConstants.REDIS_SINGLE:
			return ((RedisSingleProcessor) getRedisProcessor()).tryLock(key, value, expire);
		case RedisModeConstants.REDIS_CLUSTER:
			return ((RedisClusterProcessor) getRedisProcessor()).tryLock(key, value, expire);
		case RedisModeConstants.REDIS_SENTINEL:
			log.error("This operation is not implemented in the current mode , mode {}", redisMode);
			throw new RuntimeException("This operation is not implemented in the current mode");
		default:
			log.error("Failed to obtain redis schema instance params {}", redisMode);
			throw new RuntimeException("Failed to obtain redis schema instance");
		}
	}

	/**
	 * redis分布式锁-解锁
	 *
	 * @param key
	 *            分布式锁-key
	 * @param value
	 *            分布式锁-value
	 * @return true-解除锁成功/false-解除锁失败
	 */
	public boolean unLock(String key, String value) {

		switch (redisMode) {
		case RedisModeConstants.REDIS_SINGLE:
			return ((RedisSingleProcessor) getRedisProcessor()).unLock(key, value);
		case RedisModeConstants.REDIS_CLUSTER:
			return ((RedisClusterProcessor) getRedisProcessor()).unLock(key, value);
		case RedisModeConstants.REDIS_SENTINEL:
			log.error("This operation is not implemented in the current mode , mode {}", redisMode);
			throw new RuntimeException("This operation is not implemented in the current mode");
		default:
			log.error("Failed to obtain redis schema instance params {}", redisMode);
			throw new RuntimeException("Failed to obtain redis schema instance");
		}
	}

	/**
	 * 获取redis 处理器
	 *
	 * @return Object
	 */
	private Object getRedisProcessor() {

		switch (redisMode) {
		case RedisModeConstants.REDIS_SINGLE:
			return RedisAdapterAndProcessorCache.RedisProcessorMap
					.get(RedisAdapterAndProcessorCache.PROCESSOR_SINGLE_KEY);

		case RedisModeConstants.REDIS_CLUSTER:
			return RedisAdapterAndProcessorCache.RedisProcessorMap
					.get(RedisAdapterAndProcessorCache.PROCESSOR_CLUSTER_KEY);

		case RedisModeConstants.REDIS_SENTINEL:
			return RedisAdapterAndProcessorCache.RedisProcessorMap
					.get(RedisAdapterAndProcessorCache.PROCESSOR_SENTINEL_KEY);
		default:
			log.error("Failed to obtain redis schema instance params {}", redisMode);
			throw new RuntimeException("Failed to obtain redis schema instance");
		}
	}

}
