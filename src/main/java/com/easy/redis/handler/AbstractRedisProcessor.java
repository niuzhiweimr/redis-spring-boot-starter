package com.easy.redis.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * redis抽象处理类各处类必须继承此类
 *
 * @author niuzhiwei
 */
@Slf4j
public abstract class AbstractRedisProcessor {

    /**
     * 获取连接工厂
     *
     * @return
     */
    public abstract JedisConnectionFactory getConnectionFactory();

    /**
     * 关闭连接
     */
    public abstract void close();


    /**
     * 序列化
     */
    static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            return baos.toByteArray();
        } catch (Exception e) {
            log.error("redisException serialize fail ", e);
        }
        return null;
    }


    /**
     * 反序列化
     *
     * @param bytes
     * @return
     */
    static Object unSerialize(byte[] bytes) {
        ObjectInputStream ois = null;
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            log.error("redisException unSerialize fail ", e);
        }
        return null;
    }
}
