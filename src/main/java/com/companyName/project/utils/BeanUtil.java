package com.companyName.project.utils;

import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;


@Service
public class BeanUtil implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }

    public static Object getBean (String beanId) {
        return context.getBean(beanId);
    }

}
