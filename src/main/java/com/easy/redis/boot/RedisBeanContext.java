package com.easy.redis.boot;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 上下文
 *
 * @author niuzhiwei
 */

@Component
@Setter
@Getter
public class RedisBeanContext implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    
    @Override
    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        RedisBeanContext.applicationContext = applicationContext;
    }


    public static <T> T getBean(String beanName) {
        return (T) applicationContext.getBean(beanName);
    }

    public static <T> T getBean(Class<T> tClass) {
        return applicationContext.getBean(tClass);
    }

}
