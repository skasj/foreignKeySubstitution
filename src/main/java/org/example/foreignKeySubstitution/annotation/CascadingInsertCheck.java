package org.example.foreignKeySubstitution.annotation;

public @interface CascadingInsertCheck {
    Class<?> beanType();
    String methodName();
    String fieldName();
}
