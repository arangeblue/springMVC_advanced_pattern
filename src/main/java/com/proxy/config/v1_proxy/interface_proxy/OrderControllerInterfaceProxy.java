package com.proxy.config.v1_proxy.interface_proxy;

import com.proxy.app.v1.OrderControllerV1;
import com.proxy.trace.TraceStatus;
import com.proxy.trace.logtrace.LogTrace;

import lombok.RequiredArgsConstructor;


/**
 *@title : OrderControllerInterfaceProxy
 *@author : wikyubok 
 *@date : "2021-11-04 15:15:19"
 *@description : 기존의 orderControllerV1에 프록시 패턴을 적용하기 위한 클래스
*/

@RequiredArgsConstructor
public class OrderControllerInterfaceProxy implements OrderControllerV1 {

    private final OrderControllerV1 orderControllerV1;
    private final LogTrace logTrace;

    @Override
    public String request(String itemId) {
        
        TraceStatus status = null;
        
        try {
            status = logTrace.begin("OrderController.request()");
            
            // target 호출
            String result = orderControllerV1.request(itemId);
            logTrace.end(status);
            return result;
        } catch (Exception e) {

            logTrace.exception(status, e);
            throw e;

        }
        
    }

    @Override
    public String noLog() {
        return orderControllerV1.noLog();
    }
    
}
