package com.kevin.snake.bootlicense.aop;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
public @interface NeedCheck {

    String value();
}
