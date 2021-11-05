package com.proxy.config.v1_proxy;

import com.proxy.app.v2.OrderControllerV2;
import com.proxy.app.v2.OrderRepositoryV2;
import com.proxy.app.v2.OrderServiceV2;
import com.proxy.config.v1_proxy.concrete_proxy.OrderControllerConcreteProxy;
import com.proxy.config.v1_proxy.concrete_proxy.OrderRepositoryConcreteProxy;
import com.proxy.config.v1_proxy.concrete_proxy.OrderServiceConcreteProxy;
import com.proxy.trace.logtrace.LogTrace;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *@title : ConcreteProxyConfig
 *@author : wikyubok 
 *@date : "2021-11-04 17:01:20"
 *@description : 구체 클래스 프록시 패턴에 필요한 configuration 파일
*/

@Configuration
public class ConcreteProxyConfig {
    

    @Bean
    public OrderControllerV2 orderControllerV2(LogTrace logTrace) {
        OrderControllerV2 controllerImpl = new OrderControllerV2(orderServiceV2(logTrace));
        return new OrderControllerConcreteProxy(controllerImpl, logTrace);
    }


    @Bean
    public OrderServiceV2 orderServiceV2(LogTrace logTrace) {
        OrderServiceV2 serviceImpl = new OrderServiceV2(orderRepositoryV2(logTrace));
        return new OrderServiceConcreteProxy(serviceImpl, logTrace);
    
    }


    @Bean
    public OrderRepositoryV2 orderRepositoryV2(LogTrace logTrace) {
        OrderRepositoryV2 repositoryImpl = new OrderRepositoryV2();
        return new OrderRepositoryConcreteProxy(repositoryImpl, logTrace);
    }
}
