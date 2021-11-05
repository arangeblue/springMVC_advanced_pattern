package com.proxy.jdkdynamic;

import java.lang.reflect.Proxy;

import com.proxy.jdkdynamic.code.AImpl;
import com.proxy.jdkdynamic.code.AInterface;
import com.proxy.jdkdynamic.code.BImpl;
import com.proxy.jdkdynamic.code.BInterface;
import com.proxy.jdkdynamic.code.TimeInvocationHandler;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

/**
 *@title : JdkDynamicProxyTest
 *@author : wikyubok 
 *@date : "2021-11-04 17:43:49"
 *@description : JDK 동적 프록시 기술 테스트
*/


@Slf4j
public class JdkDynamicProxyTest {
    
    @Test
    @DisplayName("JDK 동적 프록시 기술 적용 테스트, CALL A")
    public void dynamicA() {

        BInterface target = new BImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        BInterface proxy = (BInterface) Proxy.newProxyInstance(BInterface.class.getClassLoader(),
                new Class[] { BInterface.class }, handler);

        proxy.call(); // handler에 있는 로직 실행, handler = TimeInvocationHandler의 invoke 실행 -> 이 때 Method가 call() -> 이 때 call()은 target의 함수 = AImpl
        log.info("targetClass = {}", target.getClass());
        log.info("proxyClass = {}", proxy.getClass());
    }

    
    @Test
    @DisplayName("JDK 동적 프록시 기술 적용 테스트, CALL B")
    public void dynamicB() {

        AInterface target = new AImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(),
                new Class[] { AInterface.class }, handler);

        proxy.call(); // handler에 있는 로직 실행, handler = TimeInvocationHandler의 invoke 실행 -> 이 때 Method가
                      // call() -> 이 때 call()은 target의 함수 = AImpl
        log.info("targetClass = {}", target.getClass());
        log.info("proxyClass = {}", proxy.getClass());
    }

}
