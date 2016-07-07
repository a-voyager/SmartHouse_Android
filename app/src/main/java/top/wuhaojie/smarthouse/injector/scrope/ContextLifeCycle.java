package top.wuhaojie.smarthouse.injector.scrope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by wuhaojie on 2016/7/7 10:35.
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ContextLifeCycle {
    String value() default "App";
}

