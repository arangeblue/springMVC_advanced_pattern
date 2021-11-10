package com.proxy.postprocessor;

import com.proxy.postprocessor.BeanPostProcessorTest.AtoBPostProcessor;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

/**
 *@title : BasicTest
 *@author : wikyubok 
 *@date : "2021-11-08 17:10:30"
 *@description : 빈 후처리기 테스트 
*/

@Slf4j
public class BasicTest {
    
    @Test
    public void basicConfig() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
                BeanPostProcessorConfig.class); // 스프링 컨테이너

        // A는 빈으로 등록되었다.
        A a = applicationContext.getBean("beanA", A.class);
        a.helloA();

        // B는 빈으로 등록되어 있지 않는다.
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                () -> applicationContext.getBean("beanB", B.class));

    }


    @Configuration
    static class BeanPostProcessorConfig {
        
        @Bean(name = "beanA")
        public A a() {
            return new A();
        }

        @Bean
        public AtoBPostProcessor helloPostProcessor() {
            return new AtoBPostProcessor();
        }

    }

    static class A {
        public void helloA() {
            log.info("helloA");
        }
    }
    
    static class B {
        public void helloB() {
            log.info("helloB");
        }
    }

}
