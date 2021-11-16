package com.proxy.config.v6_aop.aspect;

import java.lang.reflect.Method;

import com.proxy.trace.TraceStatus;
import com.proxy.trace.logtrace.LogTrace;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import lombok.extern.slf4j.Slf4j;

/**
 *@title : LogTraceAspect
 *@author : wikyubok 
 *@date : "2021-11-14 14:25:38"
 *@description : aop를 사용한 프록시 적용
*/


@Slf4j
@Aspect // 이게 있으면 Advisor로 바꿔주는 기능
public class LogTraceAspect {
    
    private final LogTrace logTrace;

    public LogTraceAspect(LogTrace logTrace) {
        this.logTrace = logTrace;
    }
    
    @Around("execution(* com.proxy.app..*(..))") // pointcut, 범위 지정, AspectJ 표현식
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable { // advice로 바꿔줌

        TraceStatus status = null;

        // log.info("target = {}", joinPoint.getTarget());
        // log.info("getArgs = {}", joinPoint.getArgs());
        // log.info("getSignature = {}", joinPoint.getSignature());

        try {
            String message = joinPoint.getSignature().toShortString();
            status = logTrace.begin(message);

            // 로직 호출
            // Object result = method.invoke(target, args);
            // invocation.proceed();
            Object result = joinPoint.proceed();
            logTrace.end(status);

            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }

    }


}
