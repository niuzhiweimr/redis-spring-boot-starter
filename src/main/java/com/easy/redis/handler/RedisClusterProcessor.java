package com.easy.redis.handler;

import com.easy.redis.boot.RedisBeanContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisCluster;

import java.util.List;
import java.util.Map;

/**
 * redis集群处理类
 * <p>
 * TODO:此类后续需要优化
 *
 * @author niuzhiwei
 */
@Slf4j
public class RedisClusterProcessor extends AbstractRedisProcessor {

    private static JedisCluster jedisCluster;

    private JedisCluster getJedisCluster() {
        if (jedisCluster == null) {
            JedisConnectionFactory jedisConnectionFactory = getConnectionFactory();
            jedisCluster = (JedisCluster) jedisConnectionFactory.getClusterConnection().getNativeConnection();
            return jedisCluster;
        }
        return jedisCluster;
    }

    /**
     * 判断key是否存在
     */
    public boolean exists(String key) {
        JedisCluster jedis;
        try {
            jedis = getJedisCluster();
            return key == null ? false : jedis.exists(key);
        } catch (Exception e) {
            log.error("redisException: ", e);
        } finally {
            close();
        }
        return false;
    }


    /**
     * 获取key值对应value长度
     *
     * @param key
     * @return
     */
    public Long length(String key) {
        JedisCluster jedis;
        try {
            jedis = getJedisCluster();
            return key == null ? null : jedis.strlen(key);
        } catch (Exception e) {
            log.error("redisException: ", e);
        } finally {
            close();
        }
        return null;
    }

    /**
     * 新增或修改
     */
    public void set(String key, Object obj) {
        JedisCluster jedis;
        try {
            if (key != null && obj != null) {
                jedis = getJedisCluster();
                jedis.set(key.getBytes("UTF-8"), serialize(obj));
            } else {
                log.error("redisException 参数值不合法");
            }
        } catch (Exception e) {
            log.error("redisException: ", e);
        } finally {
            close();
        }
    }

    /**
     * 新增或修改
     */
    public void set(String key, String value) {
        JedisCluster jedis;
        try {
            if (key != null && value != null) {
                jedis = getJedisCluster();
                jedis.set(key, value);
            } else {
                log.error("redisException 参数值不合法");
            }
        } catch (Exception e) {
            log.error("redisException: ", e);
        } finally {
            close();
        }
    }


    /**
     * 新增或修改
     *
     * @param key
     * @param value
     * @param seconds 有效期（单位：s）--正数
     */
    public void setex(String key, String value, int seconds) {
        JedisCluster jedis;
        try {
            if (key != null && value != null && seconds > 0) {
                jedis = getJedisCluster();
                jedis.setex(key, seconds, value);
            } else {
                log.error("redisException 参数值不合法");
                // throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            log.error("redisException: ", e);
        } finally {
            close();
        }
    }


    /**
     * 新增或修改
     *
     * @param key
     * @param obj
     * @param seconds 有效期（单位：s）
     */
    public void setex(String key, Object obj, int seconds) {
        JedisCluster jedis;
        try {
            if (key != null && obj != null && seconds > 0) {
                jedis = getJedisCluster();
                jedis.setex(key.getBytes("UTF-8"), seconds, serialize(obj));
            } else {
                log.error("redisException 参数值不合法");
            }
        } catch (Exception e) {
            log.error("redisException: ", e);
        } finally {
            close();
        }
    }


    /**
     * 获取key的值
     *
     * @return 字符串
     */
    public String get(String key) {
        JedisCluster jedis;
        try {
            jedis = getJedisCluster();
            return key == null ? null : jedis.get(key);
        } catch (Exception e) {
            log.error("redisException: ", e);
        } finally {
            close();
        }
        return null;
    }

    /**
     * 获取key的值
     *
     * @param keys 多个key值，示例：(key1, key2, key3...)
     * @return List集合
     */
    public List<String> get(String... keys) {
        JedisCluster jedis;
        try {
            if (keys != null) {
                jedis = getJedisCluster();
                return jedis.mget(keys);
            } else {
                log.error("redisException 参数值不合法");
            }
        } catch (Exception e) {
            log.error("redisException: ", e);
        } finally {
            close();
        }
        return null;
    }

    /**
     * 获取key的值
     *
     * @return 对象
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> clazz) {
        JedisCluster jedis;
        try {
            if (key != null) {
                jedis = getJedisCluster();
                Object obj = unSerialize(jedis.get(key.getBytes("UTF-8")));
                return (T) obj;
            } else {
                log.error("redisException 参数值不合法");
            }
        } catch (Exception e) {
            log.error("redisException: ", e);
        } finally {
            close();
        }
        return null;
    }

    /**
     * 获取key的有效期
     */
    public Long getValid(String key) {
        JedisCluster jedis;
        try {
            jedis = getJedisCluster();
            return key == null ? null : jedis.ttl(key);
        } catch (Exception e) {
            log.error("redisException: ", e);
        } finally {
            close();
        }
        return null;
    }

    /**
     * 移除key的有效期
     */
    public Long removeValid(String key) {
        JedisCluster jedis;
        try {
            jedis = getJedisCluster();
            return key == null ? null : jedis.persist(key);
        } catch (Exception e) {
            log.error("redisException: ", e);
        } finally {
            close();
        }
        return null;
    }


    /**
     * 获取Key的生存时间
     *
     * @param key
     * @return
     */
    public Long getTimeOut(String key) {
        JedisCluster jedis;
        try {
            jedis = getJedisCluster();
            return jedis.ttl(key);
        } catch (Exception e) {
            log.error("redisException" + e);
        } finally {
            close();
        }
        return null;
    }


    /**
     * 删除key
     */
    public Long delete(String key) {
        JedisCluster jedis;
        try {
            jedis = getJedisCluster();
            return key == null ? null : jedis.del(key);
        } catch (Exception e) {
            log.error("redisException: ", e);
        } finally {
            close();
        }
        return null;
    }


    /**
     * key值自增1
     *
     * @param key
     * @return 自增以后的key
     */
    public Long incr(String key) {
        JedisCluster jedis;
        try {
            jedis = getJedisCluster();
            return key == null ? null : jedis.incr(key);
        } catch (Exception e) {
            log.error("redisException: ", e);
        } finally {
            close();
        }
        return null;
    }


    /**
     * hash存储
     *
     * @param key
     * @param field 属性
     * @param value 值
     */
    public void hset(String key, String field, String value) {
        JedisCluster jedis;
        try {
            if (key != null && field != null && value != null) {
                jedis = getJedisCluster();
                jedis.hset(key, field, value);
            } else {
                log.error("redisException 参数值不合法");
            }
        } catch (Exception e) {
            log.error("redisException: ", e);
        } finally {
            close();
        }
    }

    /**
     * hash获取值
     *
     * @param key
     * @param field 属性
     * @return
     */
    public String hget(String key, String field) {
        JedisCluster jedis;
        try {
            if (key != null && field != null) {
                jedis = getJedisCluster();
                return jedis.hget(key, field);
            } else {
                log.error("redisException 参数不合法");
            }
        } catch (Exception e) {
            log.error("redisException: ", e);
        } finally {
            close();
        }
        return null;
    }

    /**
     * hash存储多个
     *
     * @param key
     * @param map
     */
    public void hmset(String key, Map<String, String> map) {
        JedisCluster jedis;
        try {
            if (key != null && map != null) {
                jedis = getJedisCluster();
                jedis.hmset(key, map);
            } else {
                log.error("redisException 参数不合法");
            }
        } catch (Exception e) {
            log.error("redisException: ", e);
        } finally {
            close();
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
        JedisCluster jedis;
        try {
            if (key != null && fields != null) {
                jedis = getJedisCluster();
                return jedis.hmget(key, fields);
            } else {
                log.error("redisException 参数不合法");
            }
        } catch (Exception e) {
            log.error("redisException: ", e);
        } finally {
            close();
        }
        return null;
    }

    /**
     * hash获取属性个数
     *
     * @param key
     * @return
     */
    public Long hlen(String key) {
        JedisCluster jedis;
        try {
            jedis = getJedisCluster();
            return key == null ? Long.valueOf(0) : jedis.hlen(key);
        } catch (Exception e) {
            log.error("redisException: ", e);
        } finally {
            close();
        }
        return null;
    }

    /**
     * hash获取全部
     *
     * @param key
     * @return
     */
    public Map<String, String> hgetAll(String key) {
        JedisCluster jedis;
        try {
            jedis = getJedisCluster();
            return key == null ? null : jedis.hgetAll(key);
        } catch (Exception e) {
            log.error("redisException: ", e);
        } finally {
            close();
        }
        return null;
    }

    /**
     * list-栈 存储
     *
     * @param key
     * @param strings
     */
    public void lpush(String key, String... strings) {
        JedisCluster jedis;
        try {
            if (key != null && strings != null) {
                jedis = getJedisCluster();
                jedis.lpush(key, strings);
            } else {
                log.error("redisException 参数不合法");
            }
        } catch (Exception e) {
            log.error("redisException: ", e);
        } finally {
            close();
        }
    }

    /**
     * list-队列 存储
     *
     * @param key
     * @param strings
     */
    public void rpush(String key, String... strings) {
        JedisCluster jedis;
        try {
            if (key != null && strings != null) {
                jedis = getJedisCluster();
                jedis.rpush(key, strings);
            } else {
                log.error("redisException 参数不合法");
            }
        } catch (Exception e) {
            log.error("redisException: ", e);
        } finally {
            close();
        }
    }

    /**
     * list-获取长度
     *
     * @param key
     * @return
     */
    public Long llen(String key) {
        JedisCluster jedis;
        try {
            jedis = getJedisCluster();
            return key == null ? Long.valueOf(0) : jedis.llen(key);
        } catch (Exception e) {
            log.error("redisException: ", e);
        } finally {
            close();
        }
        return null;
    }

    /**
     * 返回index处的元素
     *
     * @param key
     * @param index
     * @return
     */
    public String lindex(String key, long index) {
        JedisCluster jedis;
        try {
            if (key != null && index > 0) {
                jedis = getJedisCluster();
                return jedis.lindex(key, index);
            } else {
                log.error("redisException 参数不合法");
            }
        } catch (Exception e) {
            log.error("redisException: ", e);
        } finally {
            close();
        }
        return null;
    }

    /**
     * list-获取所有元素
     *
     * @param key
     * @return
     */
    public List<String> lrangeAll(String key) {
        return lrange(key, 0, -1);
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
        JedisCluster jedis;
        try {
            if (key != null && start > 0 && end > 0) {
                jedis = getJedisCluster();
                return jedis.lrange(key, start, end);
            } else {
                log.error("redisException 参数不合法");
            }
        } catch (Exception e) {
            log.error("redisException: ", e);
        } finally {
            close();
        }
        return null;
    }

    /**
     * list-移除元素（出栈）
     *
     * @param key
     * @return
     */
    public String lpop(String key) {
        JedisCluster jedis;
        try {
            jedis = getJedisCluster();
            return key == null ? null : jedis.lpop(key);
        } catch (Exception e) {
            log.error("redisException: ", e);
        } finally {
            close();
        }
        return null;
    }

    /**
     * list-移除元素（出队列）
     *
     * @param key
     * @return
     */
    public String rpop(String key) {
        JedisCluster jedis;
        try {
            jedis = getJedisCluster();
            return key == null ? null : jedis.rpop(key);
        } catch (Exception e) {
            log.error("redisException: ", e);
        } finally {
            close();
        }
        return null;
    }

    /**
     * 设置key的过期时间
     *
     * @param key
     * @param seconds
     */
    public void setTimeOut(String key, int seconds) {
        JedisCluster jedis;
        try {
            jedis = getJedisCluster();
            jedis.expire(key, seconds);
        } catch (Exception e) {
            log.error("redisException" + e);
        } finally {
            close();
        }
    }


    /**
     * 新增或修改-key存在，不存储；反之 注： setnx(key, value)方法在set字段时会先判断key是否存在，key值存在-不做任何操作并返回0；key值不存在-存储key并返回1
     * 但是setnx存储key-value的同时不能设置有效期，所以需要用expire()手动设置有效期 --> expire(key, seconds):设置有效期
     *
     * @param key
     * @param value
     * @param seconds 有效期（单位：s）
     */
    @Deprecated
    public void setnx(String key, String value, int seconds) {
        JedisCluster jedis;
        try {
            if (key != null && value != null && seconds > 0) {
                jedis = getJedisCluster();
                if (jedis.setnx(key, value) > 0) {
                    jedis.expire(key, seconds);
                } else {
                    log.debug("redisInfo " + key + "已存在,不存储");
                }
            } else {
                log.error("redisException 参数值不合法");
            }
        } catch (Exception e) {
            log.error("redisException: ", e);
        } finally {
            close();
        }
    }


    @Deprecated
    public int setnxAndReturnSuccess(String key, String value, int seconds) {
        JedisCluster jedis;
        try {
            if (key != null && value != null && seconds > 0) {
                jedis = getJedisCluster();
                if (jedis.setnx(key, value) > 0) {
                    jedis.expire(key, seconds);
                    return 1;
                } else {
                    log.debug("redisInfo " + key + "已存在,不存储");
                    return 0;
                }
            } else {
                log.error("redisException 参数值不合法");
            }
        } catch (Exception e) {
            log.error("redisException: ", e);
        } finally {
            close();
        }
        return 0;
    }

    /**
     * 新增或修改-key存在，不存储；反之
     *
     * @param key
     * @param obj
     * @param seconds 有效期（单位：s）
     */
    @Deprecated
    public void setnx(String key, Object obj, int seconds) {
        JedisCluster jedis;
        try {
            if (key != null && obj != null && seconds > 0) {
                jedis = getJedisCluster();
                if (jedis.setnx(key.getBytes("UTF-8"), serialize(obj)) > 0) {
                    jedis.expire(key.getBytes("UTF-8"), seconds);
                } else {
                    log.debug("redisInfo " + key + "已存在,不存储");
                }
            } else {
                log.error("redisException 参数值不合法");
            }
        } catch (Exception e) {
            log.error("redisException: ", e);
        } finally {
            close();
        }
    }


    @Override
    public JedisConnectionFactory getConnectionFactory() {

        return RedisBeanContext.getBean(JedisConnectionFactory.class);
    }

    @Override
    public void close() {

    }

}
