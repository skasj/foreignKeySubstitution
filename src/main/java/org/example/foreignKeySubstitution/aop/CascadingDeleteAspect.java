package org.example.foreignKeySubstitution.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.foreignKeySubstitution.annotation.CascadingDelete;
import org.example.foreignKeySubstitution.annotation.CascadingDeleteList;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
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
    @Around(value = "@annotation(cascadingDeleteList)")
    public Object process(ProceedingJoinPoint pjp, CascadingDeleteList cascadingDeleteList) throws RuntimeException {
        Object result = null;
        // 先获得idList
        for (CascadingDelete cascadingDelete : cascadingDeleteList.value()) {
            // 先删除有外键关系的关联表
            Object bean = applicationContext.getBean(cascadingDelete.beanType());
            if (null == invokeDeleteListMethod(bean, cascadingDelete.methodName(), pjp.getArgs())) {
                return null;
            }
        }
        try {
            // 再删除本表
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
