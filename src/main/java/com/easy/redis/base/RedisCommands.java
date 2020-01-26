package com.easy.redis.base;

import redis.clients.jedis.ScanResult;

import java.util.List;
import java.util.Map;

/**
 * redis 操作函数顶级接口
 *
 * @author niuzhiwei
 */
public interface RedisCommands {

    /**
     * 判断redis#key键是否存在
     *
     * @param key redis#key
     * @return
     */
    boolean exists(String key);

    /**
     * 通过redis#key判断value长度
     *
     * @param key redis#key
     * @return
     */
    Long length(String key);

    /**
     * 添加
     *
     * @param key redis#key
     * @param obj 值对象
     */
    void setObj(String key, Object obj);

    /**
     * 添加
     *
     * @param key   redis#key
     * @param value 字符串对象
     */
    void set(String key, String value);

    /**
     * 添加并设置过期时间
     *
     * @param key     redis#key
     * @param value   字符串对象
     * @param seconds 过期时间单位（S）
     */
    void setex(String key, String value, int seconds);

    /**
     * 添加并设置过期时间
     *
     * @param key     redis#key
     * @param obj     值队形
     * @param seconds 过期时间单位（S）
     */
    void setexObj(String key, Object obj, int seconds);

    /**
     * 通过key获取值
     *
     * @param key redis#key
     * @return
     */
    String get(String key);

    /**
     * 获取多个redis#key的值
     *
     * @param keys redis#key数组
     * @return
     */
    List<String> get(String... keys);

    /**
     * 正则获取key
     *
     * @param index 索引位置
     * @param regx  正则表达式
     * @return
     */
    ScanResult<String> scan(int index, String regx);

    /**
     * 正则获取key
     *
     * @param regx 正则表达式
     * @return
     */
    ScanResult<String> scan(String regx);

    /**
     * 获取 redis#key值并转换为目标对象
     *
     * @param key   redis#key
     * @param clazz 目标对象
     * @param <T>   对象类型
     * @return
     */
    <T> T getObj(String key, Class<T> clazz);

    /**
     * 获取redis#key剩余过期时间
     *
     * @param key redis#key
     * @return
     */
    Long getValid(String key);

    /**
     * 删除redis#key过期时间
     *
     * @param key
     * @return
     */
    Long removeValid(String key);

    /**
     * 获取redis#key过期时间
     *
     * @param key redis#key
     * @return
     */
    Long getTimeOut(String key);

    /**
     * 删除redis#key
     *
     * @param key redis#key
     * @return
     */
    Long delete(String key);

    /**
     * redis#key的值自增1
     *
     * @param key
     * @return
     */
    Long incr(String key);

    /**
     * hash类型存储数据
     *
     * @param key   redis#key
     * @param field 字段名
     * @param value 字段值
     */
    void hset(String key, String field, String value);

    /**
     * hash类型获取数据
     *
     * @param key   redis#key
     * @param field 字段名
     * @return String
     */
    String hget(String key, String field);

    /**
     * hash类型删除数据
     *
     * @param key   redis#key
     * @param field 字段名
     * @return
     */
    Long hdel(String key, String field);

    /**
     * hash类型批量添加
     *
     * @param key redis#key
     * @param map hashMap对象
     */
    void hmset(String key, Map<String, String> map);

    /**
     * hash类型批量获取
     *
     * @param key    redis#key
     * @param fields 字段名数组
     * @return
     */
    List<String> hmget(String key, String... fields);

    /**
     * hash类型获取属性个数
     *
     * @param key redis#key
     * @return
     */
    Long hlen(String key);

    /**
     * 通过redis#key获取说有数据
     *
     * @param key redis#key
     * @return
     */
    Map<String, String> hgetAll(String key);

    /**
     * 从左边入列表
     *
     * @param key     redis#key
     * @param strings 入列表的值
     */
    void lpush(String key, String... strings);

    /**
     * 从右边入列表
     *
     * @param key     redis#key
     * @param strings 入列表的值
     */
    void rpush(String key, String... strings);

    /**
     * 返回列表长度
     *
     * @param key redis#key
     * @return
     */
    Long llen(String key);

    /**
     * 返回index下标的元素
     *
     * @param key   redis#key
     * @param index 下标
     * @return
     */
    String lindex(String key, long index);

    /**
     * 获取key列表所有元素
     *
     * @param key redis#key
     * @return
     */
    List<String> lrangeAll(String key);

    /**
     * 获取列表指定范围的元素
     *
     * @param key   redis#key
     * @param start 开始位置
     * @param end   结束位置
     * @return
     */
    List<String> lrange(String key, long start, long end);

    /**
     * 弹出列表头部元素
     *
     * @param key redis#key
     * @return
     */
    String lpop(String key);

    /**
     * 弹出列表尾部元素
     *
     * @param key redis#key
     * @return
     */
    String rpop(String key);

    /**
     * redis阻塞队列
     *
     * @param key     redis#key
     * @param seconds 过期时间单位（S）
     * @return
     */
    List<String> blpop(String key, int seconds);

    /**
     * 设置key的过期时间
     *
     * @param key     redis#key
     * @param seconds 过期时间单位（S）
     */
    void setTimeOut(String key, int seconds);

    /**
     * redis 分布式锁-锁定
     *
     * @param key    分布式锁Key
     * @param value  分布式锁value
     * @param expire 分布式锁过期时间
     * @return true-获取锁成功/false-获取锁失败
     */
    boolean tryLock(String key, String value, long expire);

    /**
     * redis 分布式锁-解锁
     *
     * @param key   分布式锁Key
     * @param value 分布式锁value
     * @return true-解除锁成功/false-解除锁失败
     */
    boolean unLock(String key, String value);
    
    /**
     * redis 分布式锁-锁定
     *
     * @param key          分布式等待锁Key
     * @param expireSecond 持有锁超时毫秒数
     * @param waitSecond   等待锁超时秒数
     * @param flag         线程标识
     * @return true-获取锁成功/false-获取锁失败
     */
    boolean tryLockWait(String key, long expireSecond, int waitSecond, String flag);

    /**
     * redis 分布式锁-解锁
     *
     * @param key  分布式等待锁Key
     * @param flag 线程标识
     * @return true-解除锁成功/false-解除锁失败
     */
    boolean unlockWait(String key, String flag);

    /**
     * 获取线程标识
     *
     * @return
     */
    String getThreadFlag();

}
