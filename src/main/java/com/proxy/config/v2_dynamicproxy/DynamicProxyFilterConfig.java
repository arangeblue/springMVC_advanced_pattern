package com.proxy.config.v2_dynamicproxy;

import java.lang.reflect.Proxy;

import com.proxy.app.v1.OrderControllerV1;
import com.proxy.app.v1.OrderControllerV1Impl;
import com.proxy.app.v1.OrderRepositoryV1;
import com.proxy.app.v1.OrderRepositoryV1Impl;
import com.proxy.app.v1.OrderServiceV1;
import com.proxy.app.v1.OrderServiceV1Impl;
import com.proxy.config.v2_dynamicproxy.handler.LogTraceBasicHandler;
import com.proxy.config.v2_dynamicproxy.handler.LogTraceFilterHandler;
import com.proxy.trace.logtrace.LogTrace;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *@title : DynamicProxyFilterConfig
 *@author : wikyubok 
 *@date : "2021-11-05 15:40:17"
 *@description : 동적 프록시 기술을 직접 빈에 등록하여 v1에 적용, 이 때 filter를 적용하여 원하는 pattern의 이름을 가진 class만 적용
*/

@Configuration
public class DynamicProxyFilterConfig {
    

    private static final String[] PATTERNS = { "request*", "order*", "save*" };


    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace logTrace) {
        OrderControllerV1Impl orderController = new OrderControllerV1Impl(orderServiceV1(logTrace));
        OrderControllerV1 proxy = (OrderControllerV1) Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(),
                                new Class[]{OrderControllerV1.class},
                new LogTraceFilterHandler(orderController, logTrace, PATTERNS));


        return proxy;
    }


    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace logTrace) {
        OrderServiceV1Impl orderService = new OrderServiceV1Impl(orderRepositoryV1(logTrace));
        OrderServiceV1 proxy = (OrderServiceV1) Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(),
                                new Class[]{OrderServiceV1.class},
                new LogTraceFilterHandler(orderService, logTrace, PATTERNS));
                                
        return proxy;
    }


    @Bean  // orderRepositoryV1의 이름으로 빈 등록, 반환 값이 proxy
    public OrderRepositoryV1 orderRepositoryV1(LogTrace logTrace) {
        OrderRepositoryV1Impl orderRepository = new OrderRepositoryV1Impl();

        OrderRepositoryV1 proxy = (OrderRepositoryV1) Proxy.newProxyInstance(OrderRepositoryV1.class.getClassLoader(),
                new Class[] { OrderRepositoryV1.class }, 
                new LogTraceFilterHandler(orderRepository, logTrace, PATTERNS));

        return proxy;
    }
    


}
