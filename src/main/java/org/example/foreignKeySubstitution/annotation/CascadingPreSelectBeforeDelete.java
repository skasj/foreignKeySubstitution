package org.example.foreignKeySubstitution.annotation;

public @interface CascadingPreSelectBeforeDelete {
    Class<?> beanType();

    String methodName();

    Class<?>[] argsClassType() ;
}
