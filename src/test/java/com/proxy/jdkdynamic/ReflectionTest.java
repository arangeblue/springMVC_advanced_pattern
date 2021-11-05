package com.proxy.jdkdynamic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

/**
 *@title : ReflectionTest
 *@author : wikyubok 
 *@date : "2021-11-04 17:10:48"
 *@description : 동적 프록시 기술을 사용하기 위한 리플렉션 테스트, 리플렉션의 오류는 런타임에 발견된다.
*/


@Slf4j
public class ReflectionTest {
    
    @Test
    @DisplayName("리플렉션 테스트, 리플렉션 적용 x")
    public void reflectionV0() {

        Hello target = new Hello();

        // 공통 로직 1 시작
        log.info("start");
        String resultA = target.callA(); // 이쪽만 동적 처리 해버리면?
        log.info("end, resultA = {}", resultA);
        // 공통 로직 1 종료

        // 공통 로직 2 시작
        log.info("start");
        String resultB = target.callB(); // 이쪽만 동적 처리 해버리면?
        log.info("end, resultB = {}", resultB);
        // 공통 로직 2 종료

    }
    
    @Test
    @DisplayName("리플렉션 테스트, 공통부분 리플렉션 적용 o")
    public void reflectionV1() throws Exception {

        // 클래스 정보
        Class<?> classHello = Class.forName("com.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();

        // 클래스 안에 있는 callA 메서드 정보
        Method methodCallA = classHello.getMethod("callA");
        Object result1 = methodCallA.invoke(target);
        log.info("result1 = {}", result1);

        // 클래스 안에 있는 callB 메서드 정보
        Method methodCallB = classHello.getMethod("callB");
        Object result2 = methodCallB.invoke(target);
        log.info("result2 = {}", result2);

    }


    @Test
    @DisplayName("리플렉션 테스트, 공통부분 리플렉션 적용 o, method로 추출하여 처리")
    public void reflectionV2() throws Exception {

        // 클래스 정보
        Class<?> classHello = Class.forName("com.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();

        // 클래스 안에 있는 callA 메서드 정보
        Method methodCallA = classHello.getMethod("callA");
        dynamicCalll(methodCallA, target);
        
        

        // 클래스 안에 있는 callB 메서드 정보
        Method methodCallB = classHello.getMethod("callB");
        dynamicCalll(methodCallB, target);

    }

    private void dynamicCalll(Method method, Object target) throws Exception {
        log.info("start");
        Object result = method.invoke(target);
        log.info("result = {}", result);

    }

    






    
    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }
    }

}
