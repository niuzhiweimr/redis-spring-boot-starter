package com.easy.redis.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import java.io.*;

/**
 * redis抽象处理类各处类必须继承此类
 *
 * @author niuzhiwei (niuzhiwei@sinoiov.com)
 */
@Slf4j
public abstract class AbstractRedisProcessor implements RedisCommands {

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
    protected static byte[] serialize(Object object) {
        ObjectOutputStream objectOutputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            log.error("etcRedis serialize Object error: ", e);
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
            } catch (IOException e) {
                log.error("etcRedis outputStream close error: ", e);
            }
        }
        return null;
    }


    /**
     * 反序列化
     *
     * @param bytes
     * @return
     */
    protected static Object unSerialize(byte[] bytes) {
        ObjectInputStream objectInputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bytes);
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return objectInputStream.readObject();
        } catch (Exception e) {
            log.error("etcRedis unSerialize Object error: ", e);
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (byteArrayInputStream != null) {
                    byteArrayInputStream.close();
                }
            } catch (IOException e) {
                log.error("etcRedis inputStream close error: ", e);
            }
        }
        return null;
    }
}
