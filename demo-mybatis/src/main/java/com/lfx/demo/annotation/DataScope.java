package com.lfx.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author <a href="mailto:idler41@163.com">linfuxin</a> created by 2022-12-03 10:10:52
 */
@Inherited
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataScope {

    String requestParam() default "-undefined-";

    String scopeParam() default "orgIds";

    String argIndex() default "arg0";
}
