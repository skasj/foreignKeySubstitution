package org.example.foreignKeySubstitution.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface CascadingInsertCheck {
    Class<?> beanType();

    String methodName();

    int paramNo() default 0;

    String fieldName() default "";

    Class<?> fieldType() default Integer.class;
}
