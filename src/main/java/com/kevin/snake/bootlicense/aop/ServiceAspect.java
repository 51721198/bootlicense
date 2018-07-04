package com.kevin.snake.bootlicense.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by liudun on 2017/6/4.
 */
@Aspect
@Component
@Slf4j
public class ServiceAspect {

    @Pointcut("execution(* com.kevin.snake.bootlicense.service.*.*.*(..))")
    public void cutService() {
    }

    @Before("cutService()")
    public void printAop(JoinPoint point) {
        log.info("AOP 拦截 service 调用!!!");
    }
}
