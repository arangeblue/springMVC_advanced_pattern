package com.proxy.app.v1;


/**
 * @title : OrderServiceV1Impl
 * @author : wikyubok
 * @date : "2021-11-02 16:38:07"
 * @description : 프록시 패턴 v1 orderService 구현체
 */


public class OrderServiceV1Impl implements OrderServiceV1 {

    private final OrderRepositoryV1 orderRepositoryV1;

    public OrderServiceV1Impl(OrderRepositoryV1 orderRepositoryV1) {
        this.orderRepositoryV1 = orderRepositoryV1;
    }

    @Override
    public void orderItem(String itemId) {
     
        orderRepositoryV1.save(itemId);

    }

}
