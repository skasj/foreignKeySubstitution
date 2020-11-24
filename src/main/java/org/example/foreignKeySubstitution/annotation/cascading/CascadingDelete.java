package org.example.foreignKeySubstitution.annotation.cascading;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface CascadingDelete {
    Class<?> beanType();

    String methodName();

}
