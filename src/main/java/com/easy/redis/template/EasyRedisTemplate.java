package com.easy.redis.template;

import com.easy.redis.adapter.RedisAdapter;
import com.easy.redis.annotation.Describe;
import com.easy.redis.cache.RedisAdapterAndProcessorCache;

import java.util.List;
import java.util.Map;

/**
 * redis 操作模板类
 *
 * @author niuzhiwei
 */
public class EasyRedisTemplate {


    @Describe(value = "判断key是否存在")
    public final boolean exists(String key) {
        return getRedisAdapter().exists(key);
    }

    @Describe(value = "获取key值对应value长度")
    public final Long getLength(String key) {
        return getRedisAdapter().length(key);
    }

    @Describe(value = "新增 value为Object类型")
    public final void set(String key, Object obj) {
        getRedisAdapter().set(key, obj);
    }

    @Describe(value = "新增 value为String类型")
    public final void set(String key, String value) {
        getRedisAdapter().set(key, value);
    }

    @Describe(value = "新增 value为String类型 seconds 有效期（单位：s）--正数 ")
    public final void set(String key, String value, int seconds) {
        getRedisAdapter().setex(key, value, seconds);
    }

    @Describe(value = "新增 value为Object类型 seconds 有效期（单位：s）--正数 ")
    public final void set(String key, Object obj, int seconds) {
        getRedisAdapter().setex(key, obj, seconds);
    }

    @Describe(value = "获取key的值 ")
    public final String get(String key) {
        return getRedisAdapter().get(key);
    }

    @Describe(value = "获取多个key值")
    public final List<String> get(String... keys) {
        return getRedisAdapter().get(keys);
    }

    @Describe(value = "获取key的值 class为需要转换为的对象 ")
    public final <T> T get(String key, Class<T> clazz) {
        return getRedisAdapter().get(key, clazz);
    }

    @Describe(value = "获取key的有效期")
    public final Long getValid(String key) {
        return getRedisAdapter().getValid(key);
    }

    @Describe(value = "删除key的有效期")
    public final Long delValid(String key) {
        return getRedisAdapter().removeValid(key);
    }

    @Describe(value = "获取Key的生存时间")
    public final Long ttl(String key) {
        return getRedisAdapter().getTimeOut(key);
    }

    @Describe(value = "删除key")
    public final Long del(String key) {
        return getRedisAdapter().delete(key);
    }

    @Describe(value = "key值自增1")
    public final Long incr(String key) {
        return getRedisAdapter().incr(key);
    }

    @Describe(value = "hash存储")
    public final void hset(String key, String field, String value) {
        getRedisAdapter().hset(key, field, value);
    }

    @Describe(value = "hash获取值")
    public final String hget(String key, String field) {
        return getRedisAdapter().hget(key, field);
    }

    @Describe(value = "hash存储多个")
    public final void hmset(String key, Map<String, String> map) {
        getRedisAdapter().hmset(key, map);
    }

    @Describe(value = "hash获取多个")
    public final List<String> hmget(String key, String... fields) {
        return getRedisAdapter().hmget(key, fields);
    }

    @Describe(value = "hash获取属性个数")
    public final Long hlen(String key) {
        return getRedisAdapter().hlen(key);
    }

    @Describe(value = "hash获取全部")
    public final Map<String, String> hgetAll(String key) {
        return getRedisAdapter().hgetAll(key);
    }

    @Describe(value = "list-栈 存储")
    public final void lpush(String key, String... strings) {
        getRedisAdapter().lpush(key, strings);
    }

    @Describe(value = "list-队列 存储")
    public final void rpush(String key, String... strings) {
        getRedisAdapter().rpush(key, strings);
    }

    @Describe(value = "list-获取长度")
    public final Long llen(String key) {
        return getRedisAdapter().llen(key);
    }

    @Describe(value = "返回index处的元素")
    public final String lindex(String key, long index) {
        return getRedisAdapter().lindex(key, index);
    }

    @Describe(value = "list-获取所有元素")
    public final List<String> lrangeAll(String key) {
        return getRedisAdapter().lrangeAll(key);
    }

    @Describe(value = "list-获取指定区间元素")
    public final List<String> lrange(String key, long start, long end) {
        return getRedisAdapter().lrange(key, start, end);
    }

    @Describe(value = "list-移除元素（出栈）")
    public final String lpop(String key) {
        return getRedisAdapter().lpop(key);
    }

    @Describe(value = "list-移除元素（出队列）")
    public final String rpop(String key) {
        return getRedisAdapter().rpop(key);
    }

    @Describe(value = "设置key的过期时间")
    public final void expire(String key, int seconds) {
        getRedisAdapter().setTimeOut(key, seconds);
    }

    @Describe(value = "分布式锁新功能不建议使用此函数，后期可能会重新实现")
    @Deprecated
    public final void setNx(String key, String value, int seconds) {
        getRedisAdapter().setnx(key, value, seconds);
    }

    @Describe(value = "分布式锁带返回值，新功能不建议使用此函数，后期可能会重新实现")
    @Deprecated
    public final int setNxReturn(String key, String value, int seconds) {
        return getRedisAdapter().setnxAndReturnSuccess(key, value, seconds);
    }

    @Describe(value = "分布式锁，存入对象，有效期（单位：s）新功能不建议使用此函数，后期可能会重新实现 ")
    @Deprecated
    public final void setNx(String key, Object obj, int seconds) {
        getRedisAdapter().setnx(key, obj, seconds);
    }

    private RedisAdapter getRedisAdapter() {
        return RedisAdapterAndProcessorCache.RedisAdapterMap.get(RedisAdapterAndProcessorCache.ADAPTER_KEY);
    }

}
