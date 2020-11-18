package com.example.demo.class2;

import java.lang.annotation.*;

/**
 * Rxbus
 * Created by gorden on 2016/7/23.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Subscribe {
    int code() default -1;

    ThreadMode threadMode() default ThreadMode.CURRENT_THREAD;
}
