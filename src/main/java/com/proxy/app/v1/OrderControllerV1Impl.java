package com.proxy.app.v1;


/**
 * @title : OrderControllerV1Impl
 * @author : wikyubok
 * @date : "2021-11-02 16:37:32"
 * @description : 프록시 패턴 v1 orderController 구현체
 */

public class OrderControllerV1Impl implements OrderControllerV1{

    private final OrderServiceV1 orderServiceV1;

    public OrderControllerV1Impl(OrderServiceV1 orderServiceV1) {
        this.orderServiceV1 = orderServiceV1;
    }

    @Override
    public String request(String itemId) {
        orderServiceV1.orderItem(itemId);
        return "ok";
    }


    @Override
    public String noLog() {
        
        return "ok";
    }
    
}
