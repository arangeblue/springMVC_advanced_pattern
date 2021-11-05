package com.proxy.config.v1_proxy.concrete_proxy;

import com.proxy.app.v2.OrderServiceV2;
import com.proxy.trace.TraceStatus;
import com.proxy.trace.logtrace.LogTrace;


/**
 *@title : OrderServiceConcreteProxy
 *@author : wikyubok 
 *@date : "2021-11-04 17:01:44"
 *@description : 구체 클래스 프록시 패턴을 적용할 프록시 파일
*/

public class OrderServiceConcreteProxy extends OrderServiceV2 {

    private final OrderServiceV2 orderServiceV2;
    private final LogTrace logTrace;

    public OrderServiceConcreteProxy(OrderServiceV2 orderServiceV2, LogTrace logTrace) {
        super(null);
        this.orderServiceV2 = orderServiceV2;
        this.logTrace = logTrace;
    }

    @Override
    public void orderItem(String itemId) {
        TraceStatus status = null;

        try {
            status = logTrace.begin("OrderService.orderItem()");

            // target 호출
            orderServiceV2.orderItem(itemId);
            logTrace.end(status);

        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }

    

}

