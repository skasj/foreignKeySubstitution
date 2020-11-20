package org.example.foreignKeySubstitution.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface CascadingDelete {
    Class<?> beanType();

    String methodName();

    boolean directDelete() default true;
}
