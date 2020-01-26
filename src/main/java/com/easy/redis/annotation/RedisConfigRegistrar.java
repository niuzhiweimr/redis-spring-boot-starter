package com.easy.redis.annotation;

import com.easy.redis.adapter.RedisAdapter;
import com.easy.redis.core.RedisEnvironment;
import com.easy.redis.handler.RedisClusterProcessor;
import com.easy.redis.handler.RedisSentinelProcessor;
import com.easy.redis.handler.RedisSingleProcessor;
import com.easy.redis.util.BeanRegisterUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * redis 配置初始化
 *
 * @author niuzhiwei
 */
public class RedisConfigRegistrar implements ImportBeanDefinitionRegistrar, BeanFactoryAware {


    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {

        AnnotationAttributes attributes = AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(
                EnableRedis.class.getName()));

        String redisMode = attributes.getString("value");

        DefaultListableBeanFactory defaultListableBeanFactory = BeanRegisterUtil.getDefaultListableBeanFactory();
        BeanRegisterUtil.register(defaultListableBeanFactory, RedisEnvironment.class);
        BeanRegisterUtil.register(defaultListableBeanFactory, RedisAdapter.class);
        BeanRegisterUtil.register(defaultListableBeanFactory, RedisSingleProcessor.class);
        BeanRegisterUtil.register(defaultListableBeanFactory, RedisClusterProcessor.class);
        BeanRegisterUtil.register(defaultListableBeanFactory, RedisSentinelProcessor.class);
        BeanRegisterUtil.register(defaultListableBeanFactory, RedisEnvironment.class);

        after(redisMode);
    }

    /**
     * 后置操作 添加redis模式和修改redis连接工厂是否初始化
     *
     * @param redisMode {@see
     *                  {@link com.easy.redis.adapter.RedisAdapter#redisMode}
     *                  {@link com.easy.redis.core.RedisEnvironment#redisConnectionFactoryIsInit}
     *                  }
     */
    private void after(String redisMode) {
        RedisEnvironment redisEnvironment = (RedisEnvironment) BeanRegisterUtil.beanFactory.getBean(RedisEnvironment.class.getName());
        redisEnvironment.setRedisConnectionFactoryIsInit(Boolean.TRUE);
        RedisAdapter redisAdapter = (RedisAdapter) BeanRegisterUtil.beanFactory.getBean(RedisAdapter.class.getName());
        redisAdapter.setRedisMode(redisMode);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        BeanRegisterUtil.setBeanFactory(beanFactory);
    }

}
