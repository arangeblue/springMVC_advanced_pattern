package com.proxy.postprocessor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

/**
 *@title : BeanPostProcessorTest
 *@author : wikyubok 
 *@date : "2021-11-10 21:59:16"
 *@description : 후처리기로 beanA를 Bclass로 바꾸는 테스트
*/


@Slf4j
public class BeanPostProcessorTest {


    @Test
    public void basicConfig() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
                BeanPostProcessorConfig.class); // 스프링 컨테이너

        // beanA이름으로 B가 등록된다.
        B b = applicationContext.getBean("beanA", B.class);
        b.helloB();

        
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

    static class AtoBPostProcessor implements BeanPostProcessor {

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            
            log.info("beanName = {}, bean = {}", beanName, bean);

            if (bean instanceof A) {
                return new B();
            }
            return bean;

        }
        
    }
}
