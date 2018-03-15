package com.kevin.snake.bootlicense.util.annotation;

import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmpty {

    String message() default "not_empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
