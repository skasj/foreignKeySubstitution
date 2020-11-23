package org.example.foreignKeySubstitution.annotation;

public @interface CascadingSelect {
    Class<?> beanType();

    String methodName();

    Class<?>[] argsClassType() ;
}
