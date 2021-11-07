package com.proxy.config.v2_dynamicproxy.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.proxy.trace.TraceStatus;
import com.proxy.trace.logtrace.LogTrace;

import org.springframework.util.PatternMatchUtils;


/**
 *@title : LogTraceFilterHandler
 *@author : wikyubok 
 *@date : "2021-11-05 16:01:42"
 *@description : no-log와 같이 log를 출력하지 않는 메서드에 대해서 필터링하여 동적 프록시 기술 적용
*/


public class LogTraceFilterHandler implements InvocationHandler {
    
    private final Object target;
    private final LogTrace logTrace;
    private final String[] patterns; 



    public LogTraceFilterHandler(Object target, LogTrace logTrace, String[] patterns) {
        this.target = target;
        this.logTrace = logTrace;
        this.patterns = patterns;
    }





    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 메서드 이름 
        String methodName = method.getName();
        
        // save, request, reque**, *est
        if (!PatternMatchUtils.simpleMatch(patterns, methodName)) {
            return method.invoke(target, args);
        }
        

        TraceStatus status = null;

        try {
            String message = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
            status = logTrace.begin(message);

            // 로직 호출
            Object result = method.invoke(target, args);
            logTrace.end(status);

            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
        
    }
    
}
