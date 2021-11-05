package com.proxy.config.v1_proxy.concrete_proxy;

import com.proxy.app.v2.OrderRepositoryV2;
import com.proxy.trace.TraceStatus;
import com.proxy.trace.logtrace.LogTrace;

import lombok.RequiredArgsConstructor;

/**
 *@title : OrderRepositoryConcreteProxy
 *@author : wikyubok 
 *@date : "2021-11-04 16:35:41"
 *@description : orderRepositoryv2에 구체 클래스 프록시 적용을 위한 파일
*/


@RequiredArgsConstructor
public class OrderRepositoryConcreteProxy extends OrderRepositoryV2 {


    private final OrderRepositoryV2 orderRepositoryV2;
    private final LogTrace logTrace;
    private TraceStatus begin;

    @Override
    public void save(String itemId) {
     
        TraceStatus status = null;

        try {
            status = logTrace.begin("orderRepository.request()");

            // Target 호출
            orderRepositoryV2.save(itemId);
            logTrace.end(status);

        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
     
    }
    
}
