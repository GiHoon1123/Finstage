package io.dustin.salesmgmt.common.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface GraphApiAdapter {

    @AliasFor(annotation = Component.class)
    String value() default  "";
}
