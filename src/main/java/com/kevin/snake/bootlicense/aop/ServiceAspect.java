package com.kevin.snake.bootlicense.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by liudun on 2017/6/4.
 */
@Aspect
@Component
public class ServiceAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceAspect.class);

    @Pointcut("execution(* com.vico.license.service.*.*.*(..))")
    public void cutService() {
    }

    @Before("cutService()")
    public void printAop(JoinPoint point) {
        LOGGER.info("AOP 拦截 service 调用!!!");
    }
}
