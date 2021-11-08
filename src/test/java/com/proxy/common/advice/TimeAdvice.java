package com.proxy.common.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import lombok.extern.slf4j.Slf4j;

/**
 *@title : TimeAdvice
 *@author : wikyubok 
 *@date : "2021-11-07 16:26:27"
 *@description : 스프링에서 제공하는 proxyFactory에 사용하는 Advice를 위한 코드
*/


@Slf4j
public class TimeAdvice implements MethodInterceptor {
    
    
    
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();

        // 로직 실행 부분
        Object result = invocation.proceed(); // proxyFactory에서 사용하는 방법
        // Object result = methodProxy.invoke(target, args); // cglib 프록시에서 사용하는 방법
        // Object result = method.invoke(target, args); // jdk 동적 프록시 기술에서 사용하는 방법
        // 로직 실행 종료

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("TimeProxy 종료 resultTime = {}ms", resultTime);

        return result;
    }

    

}
