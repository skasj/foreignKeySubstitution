package org.example.foreignKeySubstitution.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.assertj.core.util.Arrays;
import org.example.foreignKeySubstitution.annotation.Cascading;
import org.example.foreignKeySubstitution.annotation.CascadingDelete;
import org.example.foreignKeySubstitution.annotation.CascadingDeleteList;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.List;

@Slf4j
@Aspect
@Component
public class CascadingDeleteAspect implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    /**
     * 设置应用上下文，用于获取Mapper
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 递归删除级联对象列表
     */
//    @Around(value = "@annotation(cascadingDeleteList)")
    @Around(value = "execution(* org.example.foreignKeySubstitution.mapper..delete*(..))")
    public Object process(ProceedingJoinPoint pjp) throws RuntimeException {
        Object result = null;
        // 必须实现的Mapper接口必须要有CascadingDelete注解
        Signature pjpSignature = pjp.getSignature();
        Class<?>[] interfaceList = pjpSignature.getDeclaringType().getInterfaces();
        if (!Arrays.isNullOrEmpty(interfaceList)) {
            OK:
            for (Class<?> interfaceClass : interfaceList) {
                if (null != interfaceClass.getAnnotation(Cascading.class)) {
                    for (Method method : interfaceClass.getMethods()) {
                        CascadingDeleteList cascadingDeleteList;
                        if (method.getName()
                                .equals(pjpSignature.getName())
                                && null != (cascadingDeleteList = method.getAnnotation(CascadingDeleteList.class))) {
                            for (CascadingDelete cascadingDelete : cascadingDeleteList.value()) {
                                // 先删除有外键关系的关联表
                                Object bean = applicationContext.getBean(cascadingDelete.beanType());
                                if (null == invokeDeleteListMethod(bean, cascadingDelete.methodName(), pjp.getArgs())) {
                                    return null;
                                }
                            }
                            break OK;
                        }
                    }
                }
            }
        }
        try {
            // 运行mapper方法自己的删除语句
            result = pjp.proceed();
        } catch (
                Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

    /**
     * 注意：此处args只取第一个值作为参数传入，如果有多个参数，需要进行调整
     * @since 1.0-SNAPSHOT
     */
    private Object invokeDeleteListMethod(Object bean, String methodName, Object[] args) {
        MethodType mt = MethodType.methodType(Integer.class, List.class);
        MethodHandle methodHandle;
        try {
            methodHandle = MethodHandles.lookup()
                    .findVirtual(bean.getClass(), methodName, mt);
        } catch (IllegalAccessException | NoSuchMethodException e) {
            log.error("method" + methodName + " not found", e);
            return null;
        }
        try {
            return methodHandle.invoke(bean, args[0]);
        } catch (Throwable throwable) {
            log.error("method" + methodName + " invoke error", throwable);
            throwable.printStackTrace();
            return null;
        }
    }


}
