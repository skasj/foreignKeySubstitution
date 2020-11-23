package org.example.foreignKeySubstitution.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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
    @Around(value = "execution(* org.example.foreignKeySubstitution.mapper..delete*(..))")
    public Object process(ProceedingJoinPoint pjp) throws RuntimeException {
        Object result = null;
        findCascadingDeleteMethodAndInvoke(pjp);
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
     * 寻找级联删除方法，如果找到，就调用并删除
     */
    private void findCascadingDeleteMethodAndInvoke(ProceedingJoinPoint pjp) {
        Signature pjpSignature = pjp.getSignature();
        Class<?>[] interfaceList = pjpSignature.getDeclaringType()
                .getInterfaces();
        if (interfaceList.length == 0) {
            return;
        }
        for (Class<?> interfaceClass : interfaceList) {
            // 匹配接口：级联删除接口必须带有@Cascading注解
            if (null == interfaceClass.getAnnotation(Cascading.class)) {
                continue;
            }
            for (Method method : interfaceClass.getMethods()) {
                // 匹配方法：此处不需要匹配参数类型，因为入参明确只有一个——idList，所以不用考虑重载
                if (method.getName().equals(pjpSignature.getName())) {
                    if (invokeCascadingDeleteMethodList(pjp, method))
                        return;
                }
            }
        }
    }

    private boolean invokeCascadingDeleteMethodList(ProceedingJoinPoint pjp, Method method) {
        CascadingDeleteList cascadingDeleteList = method.getAnnotation(CascadingDeleteList.class);
        if (null != cascadingDeleteList && cascadingDeleteList.value().length != 0) {
            for (CascadingDelete cascadingDelete : cascadingDeleteList.value()) {
                invokeDeleteListMethod(applicationContext.getBean(cascadingDelete.beanType()),
                        cascadingDelete.methodName(), pjp.getArgs());
            }
            return true;
        }
        return false;
    }

    /**
     * 这里只执行带有如下特征符号的方法 Integer bean.methodName(List)
     * 注意：此处args只取第一个值作为参数传入，如果有多个参数，需要进行调整
     *
     * @since 1.0-SNAPSHOT
     */
    private void invokeDeleteListMethod(Object bean, String methodName, Object[] args) {
        MethodType mt = MethodType.methodType(Integer.class, List.class);
        MethodHandle methodHandle;
        try {
            methodHandle = MethodHandles.lookup()
                    .findVirtual(bean.getClass(), methodName, mt);
        } catch (IllegalAccessException | NoSuchMethodException e) {
            log.error("method" + methodName + " not found", e);
            return;
        }
        try {
            methodHandle.invoke(bean, args[0]);
        } catch (Throwable throwable) {
            log.error("method" + methodName + " invoke error", throwable);
            throwable.printStackTrace();
        }
    }

}
