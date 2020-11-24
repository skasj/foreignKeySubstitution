package org.example.foreignKeySubstitution.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface CascadingDelete {
    Class<?> beanType();

    String methodName();

}
