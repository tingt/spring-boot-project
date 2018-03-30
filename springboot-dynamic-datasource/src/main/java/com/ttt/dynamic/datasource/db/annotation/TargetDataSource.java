package com.ttt.dynamic.datasource.db.annotation;

import java.lang.annotation.*;

/**
 * @author tutingting
 * @date 2018/3/26 下午4:32
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface TargetDataSource {
    String value();
}
