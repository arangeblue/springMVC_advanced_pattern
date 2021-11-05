package com.proxy.config;

import com.proxy.app.v2.OrderControllerV2;
import com.proxy.app.v2.OrderRepositoryV2;
import com.proxy.app.v2.OrderServiceV2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 *@title : AppV2Config
 *@author : wikyubok 
 *@date : "2021-11-02 16:47:52"
 *@description : 프록시 패턴 App V2 config 
*/

@Configuration
public class AppV2Config {

    @Bean
    public OrderControllerV2 orderControllerV2() {
        return new OrderControllerV2(orderServiceV2());
    }

    @Bean
    public OrderServiceV2 orderServiceV2() {
        return new OrderServiceV2(orderRepositoryV2());
    }

    @Bean
    public OrderRepositoryV2 orderRepositoryV2() {
        return new OrderRepositoryV2();
    }
    
}
