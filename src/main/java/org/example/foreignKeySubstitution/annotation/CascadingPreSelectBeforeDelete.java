package org.example.foreignKeySubstitution.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface CascadingPreSelectBeforeDelete {
    Class<?> beanType();

    String methodName();

    Class<?>[] argsClassType() ;
}
