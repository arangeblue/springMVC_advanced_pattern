package com.proxy.config.v1_proxy.concrete_proxy;

import com.proxy.app.v2.OrderControllerV2;
import com.proxy.trace.TraceStatus;
import com.proxy.trace.logtrace.LogTrace;


/**
 * @title : OrderControllerConcreteProxy
 * @author : wikyubok
 * @date : "2021-11-04 17:02:20"
 * @description : 구체 클래스 프록시 패턴을 적용할 프록시 파일
 */

public class OrderControllerConcreteProxy extends OrderControllerV2 {
    
    private final OrderControllerV2 orderControllerV2;
    private final LogTrace logTrace;

    public OrderControllerConcreteProxy(OrderControllerV2 orderControllerV2, LogTrace logTrace) {
        super(null);
        this.orderControllerV2 = orderControllerV2;
        this.logTrace = logTrace;
    }

    
    @Override
    public String request(String itemId) {
        
        TraceStatus status = null;

        try {
            status = logTrace.begin("OrderController.request()");

            // target 호출
            String result = orderControllerV2.request(itemId);
            logTrace.end(status);
            return result;
        } catch (Exception e) {

            logTrace.exception(status, e);
            throw e;

        }
    }
    
    @Override
    public String noLog() {
        
        return orderControllerV2.noLog();
    }
    

}
