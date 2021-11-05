package com.proxy.jdkdynamic.code;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;


/**
 *@title : TimeInvocationHandler
 *@author : wikyubok 
 *@date : "2021-11-04 17:39:08"
 *@description : 동적 jdk 프록시 적용
*/


@Slf4j
public class TimeInvocationHandler implements InvocationHandler{

    private final Object target;

    public TimeInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();

        // 로직 실행 부분
        Object result = method.invoke(target, args);

        // 로직 실행 종료

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("TimeProxy 종료 resultTime = {}ms", resultTime);

        return result;

    }
    
}
