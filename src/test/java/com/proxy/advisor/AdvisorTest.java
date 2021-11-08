package com.proxy.advisor;

import java.lang.reflect.Method;

import com.proxy.common.advice.TimeAdvice;
import com.proxy.common.service.ServiceImpl;
import com.proxy.common.service.ServiceInterface;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdvisorTest {
    
    @Test
    @DisplayName("proxyFactory에 Pointcut, advice, advisor를 사용하기")
    public void advisorTest1() {

        ServiceImpl target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(new MyPointcut(), new TimeAdvice());

        proxyFactory.addAdvisor(advisor);

        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        proxy.save();
        proxy.find();

    }
    

    @Test
    @DisplayName("직접 pointcut 만들어서 사용하기")
    public void advisorTest2() {

        ServiceImpl target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(new MyPointcut(), new TimeAdvice());

        proxyFactory.addAdvisor(advisor);

        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        proxy.save();
        proxy.find();

    }

    static class MyPointcut implements Pointcut {

        @Override
        public ClassFilter getClassFilter() {
            // TODO Auto-generated method stub
            return ClassFilter.TRUE;
        }

        @Override
        public MethodMatcher getMethodMatcher() {
            // TODO Auto-generated method stub
            return new MyMethod();
        }

    }
    
    static class MyMethod implements MethodMatcher {

        
        private String MATCH_NAME = "save";

        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            boolean result = method.getName().equals(MATCH_NAME);
            log.info("포인트컷 호출 method = {} targetClass = {}", method.getName(), targetClass);
            log.info("포인트컷 결과 result = {}", result);
            return result;
        }

        @Override
        public boolean isRuntime() {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean matches(Method method, Class<?> targetClass, Object... args) {
            // TODO Auto-generated method stub
            return false;
        }
        
    }

}
