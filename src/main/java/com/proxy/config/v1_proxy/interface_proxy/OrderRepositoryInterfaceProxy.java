package com.proxy.config.v1_proxy.interface_proxy;

import com.proxy.app.v1.OrderRepositoryV1;
import com.proxy.trace.TraceStatus;
import com.proxy.trace.logtrace.LogTrace;

import lombok.RequiredArgsConstructor;


/**
 * @title : OrderRepositoryInterfaceProxy
 * @author : wikyubok
 * @date : "2021-11-04 15:15:46"
 * @description : 기존의 orderRepositoryV1에 프록시 패턴을 적용하기 위한 클래스
 */

@RequiredArgsConstructor
public class OrderRepositoryInterfaceProxy implements OrderRepositoryV1 {

    private final OrderRepositoryV1 orderRepositoryV1;
    private final LogTrace logTrace;

    @Override
    public void save(String itemId) {
       
        TraceStatus status = null;

        try{
            status = logTrace.begin("OrderRepository.request()");

            // target 호출
            orderRepositoryV1.save(itemId);
            logTrace.end(status);

        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
        
    }
    
}
