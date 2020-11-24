package org.example.foreignKeySubstitution.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.foreignKeySubstitution.annotation.Cascading;
import org.example.foreignKeySubstitution.annotation.CascadingDelete;
import org.example.foreignKeySubstitution.annotation.CascadingDeleteList;
import org.example.foreignKeySubstitution.annotation.CascadingPreSelectBeforeDelete;
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
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 递归删除级联对象列表
     */
    @Around(value = "execution(* org.example.foreignKeySubstitution.mapper..delete*(..))")
    public Object process(ProceedingJoinPoint pjp) {
        findCascadingDeleteMethodAndInvoke(pjp);
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
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
        // 匹配接口：级联删除接口必须带有@Cascading注解
        for (Class<?> interfaceClass : interfaceList) {
            if (null != interfaceClass.getAnnotation(Cascading.class)) {
                // 匹配方法：
                for (Method method : interfaceClass.getMethods()) {
                    if (isTargetMethod(pjpSignature, method) && invokeCascadingDeleteMethodList(pjp, method)) {
                        return;
                    }
                }
            }
        }
    }

    private boolean isTargetMethod(Signature pjpSignature, Method method) {
        //此处不需要匹配参数类型，因为入参明确只有一个——idList，所以不用考虑重载
        return method.getName()
                .equals(pjpSignature.getName());
    }

    private boolean invokeCascadingDeleteMethodList(ProceedingJoinPoint pjp, Method method) {
        CascadingDeleteList cascadingDeleteList = method.getAnnotation(CascadingDeleteList.class);
        if (null == cascadingDeleteList || cascadingDeleteList.value().length == 0) {
            return false;
        }
        for (CascadingDelete cascadingDelete : cascadingDeleteList.value()) {
            invokeDeleteListMethod(applicationContext.getBean(cascadingDelete.beanType()),
                    cascadingDelete.methodName(), getArgs(pjp, cascadingDeleteList));
        }
        return true;
    }

    private Object[] getArgs(ProceedingJoinPoint pjp, CascadingDeleteList cascadingDeleteList) {
        CascadingPreSelectBeforeDelete selectMethod = cascadingDeleteList.selectMethod();
        List<?> idList = null;
        boolean isSelectMethodDefined = selectMethod.beanType() != Object.class;
        if (isSelectMethodDefined) {
            idList = invokeSelectIdListMethod(applicationContext.getBean(selectMethod
                    .beanType()), selectMethod.methodName(), selectMethod.argsClassType(), pjp.getArgs());
        }
        return idList == null ? pjp.getArgs() : new Object[]{idList};
    }

    private List<?> invokeSelectIdListMethod(Object bean, String methodName, Class<?>[] argsTypeList, Object[] args) {
        MethodType mt = getMethodType(argsTypeList, args);
        MethodHandle methodHandle;
        try {
            methodHandle = MethodHandles.lookup()
                    .findVirtual(bean.getClass(), methodName, mt);
        } catch (IllegalAccessException | NoSuchMethodException e) {
            log.error(String.format("method %s not found", methodName), e);
            return null;
        }
        Object result = invokeMethodWithArgList(bean, methodName, args, methodHandle);
        return result == null ? null : (List<?>) result;
    }

    private Object invokeMethodWithArgList(Object bean, String methodName, Object[] args, MethodHandle methodHandle) {
        Object result = null;
        try {
            if (null == args || 0 == args.length) {
                result = methodHandle.invoke(bean);
            } else {
                // select方法的参数最多只能5个，后续有需求的话可以再加
                switch (args.length) {
                    case 1:
                        result = methodHandle.invoke(bean, args[0]);
                        break;
                    case 2:
                        result = methodHandle.invoke(bean, args[0], args[1]);
                        break;
                    case 3:
                        result = methodHandle.invoke(bean, args[0], args[1], args[2]);
                        break;
                    case 4:
                        result = methodHandle.invoke(bean, args[0], args[1], args[2], args[3]);
                        break;
                    case 5:
                        result = methodHandle.invoke(bean, args[0], args[1], args[2], args[3], args[4]);
                        break;
                    default:
                        throw new IndexOutOfBoundsException("parameter too long, need extend invokeMethodWithArgList method");
                }
            }
        } catch (Throwable throwable) {
            log.error(String.format("method %s invoke error", methodName), throwable);
        }
        return result;
    }

    private MethodType getMethodType(Class<?>[] argsTypeList, Object[] args) {
        return (null == args || args.length == 0) ? MethodType.methodType(List.class) : MethodType.methodType(
                List.class, argsTypeList);
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
            log.error(String.format("method %s not found", methodName), e);
            return;
        }
        try {
            methodHandle.invoke(bean, args[0]);
        } catch (Throwable throwable) {
            log.error(String.format("method %s invoke error", methodName), throwable);
        }
    }

}
