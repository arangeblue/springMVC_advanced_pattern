package com.proxy.proxyfactory;

import static org.assertj.core.api.Assertions.assertThat;

import com.proxy.common.advice.TimeAdvice;
import com.proxy.common.service.ConcreteService;
import com.proxy.common.service.ServiceImpl;
import com.proxy.common.service.ServiceInterface;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

import lombok.extern.slf4j.Slf4j;


/**
 *@title : ProxyFactoryTest
 *@author : wikyubok 
 *@date : "2021-11-07 16:45:20"
 *@description : proxyFactory를 사용하여 jdk와 cglib을 테스트
*/


@Slf4j
public class ProxyFactoryTest {
    

    @Test
    @DisplayName("인터페이스가 존재하면 JDK 동적 프록시 기술이 사용된다.")
    public void interfaceProxy() {

        ServiceImpl target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);

        proxyFactory.addAdvice(new TimeAdvice());
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        log.info("targetClass = {}", target.getClass());
        log.info("proxyClass = {}", proxy.getClass());

        proxy.save(); // interface가 존재한다면, 자동으로 jdk 동적 프록시 기술을 사용하여 advice를 호출 및 이전에 배운 jdk 동적 프록시와 같은 효과를 줄 수 있다.

        assertThat(AopUtils.isAopProxy(proxy)).isTrue(); // proxyFactory를 사용할 때만 사용할 수 있음, AopProxy인지?
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue(); // jdk 동적 프록시를 사용했는지?
        assertThat(AopUtils.isCglibProxy(proxy)).isFalse(); // cglib 동적 프록시를 사용했는지?

    }

    
    @Test
    @DisplayName("구체클래스가 존재하면 cglib 동적 프록시 기술이 사용된다.")
    public void concreteProxy() {

        ConcreteService target = new ConcreteService();
        ProxyFactory proxyFactory = new ProxyFactory(target);

        proxyFactory.addAdvice(new TimeAdvice());
        ConcreteService proxy = (ConcreteService) proxyFactory.getProxy();

        log.info("targetClass = {}", target.getClass());
        log.info("proxyClass = {}", proxy.getClass());

        proxy.call(); // interface가 존재한다면, 자동으로 jdk 동적 프록시 기술을 사용하여 advice를 호출 및 이전에 배운 jdk 동적 프록시와 같은 효과를 줄 수 있다.

        assertThat(AopUtils.isAopProxy(proxy)).isTrue(); // proxyFactory를 사용할 때만 사용할 수 있음, AopProxy인지?
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse(); // jdk 동적 프록시를 사용했는지?
        assertThat(AopUtils.isCglibProxy(proxy)).isTrue(); // cglib 동적 프록시를 사용했는지?

    }
    

    @Test
    @DisplayName("ProxyTargetClass 옵션을 사용하면 인터페이스가 있어도 CGLIB을 사용할 수 있다. 인터페이스가 있든 없든 CGLIB기술은 사용할 수 있다.")
    // 최근 스프링버전에서는 기본적으로 ProxyTargetClass가 true로 설정되어 CGLIB을 기본적으로 사용하도록 되어 있다.
    public void proxyTargetClass() {
        
    
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true); // 항상 CGLIB 프록시 기술 사용


        proxyFactory.addAdvice(new TimeAdvice());
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        log.info("targetClass = {}", target.getClass());
        log.info("proxyClass = {}", proxy.getClass());
    

        proxy.save(); // interface가 존재한다면, 자동으로 jdk 동적 프록시 기술을 사용하여 advice를 호출 및 이전에 배운 jdk 동적 프록시와 같은 효과를 줄 수 있다.

        assertThat(AopUtils.isAopProxy(proxy)).isTrue(); // proxyFactory를 사용할 때만 사용할 수 있음, AopProxy인지?
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();// jdk 동적 프록시를 사용했는지?
        assertThat(AopUtils.isCglibProxy(proxy)).isFalse(); // cglib 동적 프록시를 사용했는지?

    }

}
