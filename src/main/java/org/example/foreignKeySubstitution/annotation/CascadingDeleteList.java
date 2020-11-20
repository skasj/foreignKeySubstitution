package org.example.foreignKeySubstitution.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CascadingDeleteList {
    CascadingDelete[] value() default {};
}
