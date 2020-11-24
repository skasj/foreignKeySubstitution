package org.example.foreignKeySubstitution.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class CascadingInsertAspect {

    @Around("execution(* org.example.foreignKeySubstitution.mapper..insert*(..))")
    public Object process(ProceedingJoinPoint pjp){
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
}
