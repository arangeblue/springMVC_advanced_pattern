package com.proxy.pattern.proxy.cglib;

import com.proxy.pattern.proxy.cglib.code.TimeMethodInterceptor;
import com.proxy.pattern.proxy.common.service.ConcreteService;

import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

import lombok.extern.slf4j.Slf4j;


/**
 *@title : CglibTest
 *@author : wikyubok 
 *@date : "2021-11-05 16:52:59"
 *@description : cglib 테스트 코드
*/

@Slf4j
public class CglibTest {
    
    @Test
    void cglib() {
        ConcreteService target = new ConcreteService();
        
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ConcreteService.class); // 구체 클래스를 상속받는다.
        enhancer.setCallback(new TimeMethodInterceptor(target));
        ConcreteService proxy = (ConcreteService) enhancer.create();
    
        log.info("targetClass = {}", target.getClass()); // 내가 만들고자 하는 구체 클래스
        log.info("proxyClass = {}", proxy.getClass()); // 내가 만들고자 하는 구체 클래스를 모방한 동적 프록시 객체

        proxy.call(); // proxy -> 구체 클래스 -> proxy 종료
    
    }

}
