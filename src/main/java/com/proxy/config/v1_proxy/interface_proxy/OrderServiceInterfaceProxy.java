package com.proxy.config.v1_proxy.interface_proxy;

import com.proxy.app.v1.OrderServiceV1;
import com.proxy.trace.TraceStatus;
import com.proxy.trace.logtrace.LogTrace;

import lombok.RequiredArgsConstructor;


/**
 * @title : OrderServiceInterfaceProxy
 * @author : wikyubok
 * @date : "2021-11-04 15:15:59"
 * @description : 기존의 orderServiceV1에 프록시 패턴을 적용하기 위한 클래스
 */

@RequiredArgsConstructor
public class OrderServiceInterfaceProxy implements OrderServiceV1 {
    
    private final OrderServiceV1 orderServiceV1;
    private final LogTrace logTrace;

    @Override
    public void orderItem(String itemId) {
        
        TraceStatus status = null;

        try{
            status = logTrace.begin("OrderService.orderItem()");

            // target 호출
            orderServiceV1.orderItem(itemId);
            logTrace.end(status);

        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
        
    }
    
}
