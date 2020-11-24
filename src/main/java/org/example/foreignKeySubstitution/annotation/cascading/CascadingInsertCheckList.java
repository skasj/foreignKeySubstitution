package org.example.foreignKeySubstitution.annotation.cascading;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CascadingInsertCheckList {
    CascadingInsertCheck[] value();
}
