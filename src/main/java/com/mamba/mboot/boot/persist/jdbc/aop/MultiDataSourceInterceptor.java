package com.mamba.mboot.boot.persist.jdbc.aop;

import com.mamba.mboot.boot.common.ThreadContext;
import com.mamba.mboot.boot.persist.jdbc.DialectRoutingDatasource;
import com.mamba.mboot.boot.persist.jdbc.annoation.MultiDataSource;
import java.lang.reflect.Method;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.AopUtils;

public class MultiDataSourceInterceptor implements MethodInterceptor {
    public MultiDataSourceInterceptor() {
    }

    public Object invoke(MethodInvocation invocation) throws Throwable {
        Class<?> targetClass = AopUtils.getTargetClass(invocation.getThis());
        Method method = AopUtils.getMostSpecificMethod(invocation.getMethod(), targetClass);
        MultiDataSource key = (MultiDataSource)method.getAnnotation(MultiDataSource.class);

        Object var6;
        try {
            ThreadContext.put(DialectRoutingDatasource.DATASOURCE_NAME_KEY, key.value());
            Object o = invocation.proceed();
            var6 = o;
        } finally {
            ThreadContext.remove(DialectRoutingDatasource.DATASOURCE_NAME_KEY);
        }

        return var6;
    }
}