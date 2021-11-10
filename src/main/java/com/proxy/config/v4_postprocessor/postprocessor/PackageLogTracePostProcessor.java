package com.proxy.config.v4_postprocessor.postprocessor;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import lombok.extern.slf4j.Slf4j;

/**
 *@title : PackageLogTracePostProcessor
 *@author : wikyubok 
 *@date : "2021-11-10 22:10:29"
 *@description : 후처리기로 프록시 적용하는 코드, 적용 패키지가 아니라면 그대로 빈 반환
*/

@Slf4j
public class PackageLogTracePostProcessor implements BeanPostProcessor {

    private final String basePackage;
    private final Advisor advisor;

    public PackageLogTracePostProcessor(String basePackage, Advisor advisor) {
        this.basePackage = basePackage;
        this.advisor = advisor;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
       
        log.info("beanName = {}, bean = {}", beanName, bean.getClass());
        
        // 프록시 적용 대상 체크
        // 프록시 적용 대상이 아니면 원본 그대로 진행
        String packageName = bean.getClass().getPackageName();

        if (!packageName.startsWith(basePackage)) {
            return bean;
        }
    
        // 프록시 대상에 대한 처리
        ProxyFactory factory = new ProxyFactory(bean);
        factory.addAdvisor(advisor);

        Object proxy = factory.getProxy();
        log.info("create proxy : target = {}, proxy = {}", bean.getClass(), proxy.getClass());
        return proxy;
    }

    


    
}
