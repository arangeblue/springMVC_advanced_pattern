package com.proxy.pattern.proxy.cglib.code;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import lombok.extern.slf4j.Slf4j;

/**
 *@title : TimeMethodInterceptor
 *@author : wikyubok 
 *@date : "2021-11-05 16:45:34"
 *@description : cglib를 통한 invoke 형식
*/


@Slf4j
public class TimeMethodInterceptor implements MethodInterceptor {
    
    private final Object target;

    public TimeMethodInterceptor(Object target) {
        this.target = target;
    }



    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();

        // 로직 실행 부분
        Object result = methodProxy.invoke(target, args);
        // Object result = method.invoke(target, args); 
        // 로직 실행 종료
 
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("TimeProxy 종료 resultTime = {}ms", resultTime);

        return result;
    }
    
}
