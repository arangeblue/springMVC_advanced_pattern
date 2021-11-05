package com.proxy.app.v2;


/**
 *@title : OrderServiceV2
 *@author : wikyubok 
 *@date : "2021-11-02 16:42:57"
 *@description : 프록시 패턴 OrderService V2 구현체
*/

public class OrderServiceV2 {
    
    private final OrderRepositoryV2 orderRepositoryV2;


    public OrderServiceV2(OrderRepositoryV2 orderRepositoryV2) {
        this.orderRepositoryV2 = orderRepositoryV2;
    }

    public void orderItem(String itemId) {

        orderRepositoryV2.save(itemId);

    }

}
