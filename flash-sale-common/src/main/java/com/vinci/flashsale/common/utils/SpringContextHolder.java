package com.vinci.flashsale.common.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext CONTEXT;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        CONTEXT = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return CONTEXT.getBean(clazz);
    }
}
