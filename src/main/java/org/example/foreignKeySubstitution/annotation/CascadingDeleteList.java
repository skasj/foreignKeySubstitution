package org.example.foreignKeySubstitution.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface CascadingDeleteList {
    CascadingDelete[] value() default {};

    CascadingPreSelectBeforeDelete selectMethod() default @CascadingPreSelectBeforeDelete(beanType = Object.class, methodName = "null", argsClassType = {});
}
