package org.example.foreignKeySubstitution.aop;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.foreignKeySubstitution.annotation.Cascading;
import org.example.foreignKeySubstitution.annotation.CascadingInsertCheck;
import org.example.foreignKeySubstitution.annotation.CascadingInsertCheckList;
import org.example.foreignKeySubstitution.exception.ForeignKeyNoExistException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.*;

@Slf4j
@Aspect
@Component
public class CascadingInsertAspect implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    /**
     * 设置应用上下文，用于获取Mapper
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @Around("execution(* org.example.foreignKeySubstitution.mapper..insert*(..))")
    public Object process(ProceedingJoinPoint pjp) throws Throwable {
        // 是否存在@CascadingInsertCheckList注解的方法，如果没有，直接跳过
        Method interfaceMethod = getInterfaceMethod(pjp);
        if (null != interfaceMethod) {
            invokeCascadingInsertCheckList(pjp, interfaceMethod);
        }
        // 运行mapper方法自己的删除语句
        return pjp.proceed();
    }

    private Method getInterfaceMethod(ProceedingJoinPoint pjp) {
        Signature pjpSignature = pjp.getSignature();
        Class<?>[] interfaceList = pjpSignature.getDeclaringType()
                .getInterfaces();
        if (interfaceList.length == 0) {
            return null;
        }
        // 匹配接口：级联删除接口必须带有@Cascading注解
        for (Class<?> interfaceClass : interfaceList) {
            if (null != interfaceClass.getAnnotation(Cascading.class)) {
                // 匹配方法：必须带有@CascadingInsertCheckList注解
                for (Method method : interfaceClass.getMethods()) {
                    if (isTargetMethod(pjpSignature, method)) {
                        return method;
                    }
                }
            }
        }
        return null;
    }

    private boolean isTargetMethod(Signature pjpSignature, Method method) {
        //此处不需要匹配参数类型，因为入参明确只有一个——idList，所以不用考虑重载
        boolean hasSameMethodName = method.getName()
                .equals(pjpSignature.getName());
        boolean hasTargetAnnotation = null != method.getAnnotation(CascadingInsertCheckList.class);
        return hasSameMethodName && hasTargetAnnotation;
    }

    private void invokeCascadingInsertCheckList(ProceedingJoinPoint pjp, Method method) throws Throwable {
        CascadingInsertCheckList cascadingInsertCheckList = method.getAnnotation(CascadingInsertCheckList.class);
        for (CascadingInsertCheck cascadingInsertCheck : cascadingInsertCheckList.value()) {
            insertCheck(pjp, cascadingInsertCheck);
        }
    }

    private void insertCheck(ProceedingJoinPoint pjp, CascadingInsertCheck cascadingInsertCheck) throws Throwable {
        List<?> idList = getIdList(pjp, cascadingInsertCheck);
        Integer numInDB = invokeInsertCheckMethod(
                applicationContext.getBean(cascadingInsertCheck.beanType()),
                cascadingInsertCheck.methodName(),
                idList);
        if (!numInDB.equals(new HashSet<>(idList).size())) {
            throw new ForeignKeyNoExistException(
                    cascadingInsertCheck.fieldName() + " is not exist in db, please refresh page or add record");
        }
    }

    /**
     * 此处限制颇多，但也符合常理：
     * (@CascadingInsertCheckList) 注解用来修饰InsertXXX方法，其参数理论上有以下几种情况：
     * 1. 将记录拆解开，每个属性为一个参数，例如 InsertXXX(Integer id,String name);
     * 2. 将记录拆解开，用Map存储属性，作为第一个参数，例如 InsertXXX(Map record);
     * 3. 将记录不拆解，第一个参数就是完整的记录，例如 InsertXXX(Record record);
     * 4. 将记录不拆解，批量插入，第一个参数是一个集合，例如 InsertXXX(List recordList);
     */
    private List<?> getIdList(ProceedingJoinPoint pjp, CascadingInsertCheck cascadingInsertCheck) throws Throwable {
        // 入参检查
        int paramNo = cascadingInsertCheck.paramNo();
        String fieldName = cascadingInsertCheck.fieldName();
        Object[] args = pjp.getArgs();
        if (null == args || 0 == args.length) {
            throw new NullPointerException("insert method args can not be empty");
        } else if (paramNo < 0 || paramNo >= args.length) {
            throw new ArrayIndexOutOfBoundsException("paramNo can out of args bounds");
        }
        Object arg = args[paramNo];
        // 1,2 两种情况
        if (StringUtils.isBlank(fieldName)) {
            return Collections.singletonList(arg);
        } else if (arg instanceof Map) {
            return Collections.singletonList(((Map<?, ?>) arg).get(fieldName));
        }
        // 3,4 两种情况
        if (arg instanceof Collection) {
            Collection<?> collection = (Collection<?>) arg;
            MethodHandle methodHandle = getGetMethodHandle(collection.iterator()
                    .next(), cascadingInsertCheck.fieldType(), fieldName);
            List<Object> result = new ArrayList<>();
            for (Object o : collection) {
                result.add(methodHandle.invoke(o));
            }
            return result;
        } else {
            MethodHandle methodHandle = getGetMethodHandle(arg, cascadingInsertCheck.fieldType(), fieldName);
            return Collections.singletonList(methodHandle.invoke(arg));
        }
    }

    @SneakyThrows
    private MethodHandle getGetMethodHandle(Object arg, Class<?> returnType, String fieldName) throws IllegalAccessException {
        MethodType mt = MethodType.methodType(returnType);
        return MethodHandles.lookup()
                .findVirtual(arg.getClass(), "get" + fieldName.substring(0, 1)
                        .toUpperCase() + fieldName.substring(1), mt);
    }

    /**
     * 这里只执行带有如下特征符号的方法 Integer bean.methodName(List)
     * 注意：此处args只取第一个值作为参数传入，如果有多个参数，需要进行调整
     *
     * @since 1.0-SNAPSHOT
     */
    private Integer invokeInsertCheckMethod(Object bean, String methodName, Object arg) throws Throwable {
        MethodType mt = MethodType.methodType(Integer.class, List.class);
        MethodHandle methodHandle;
        methodHandle = MethodHandles.lookup()
                .findVirtual(bean.getClass(), methodName, mt);
        return (Integer) methodHandle.invoke(bean, arg);
    }
}
