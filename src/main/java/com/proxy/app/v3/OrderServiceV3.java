package com.proxy.app.v3;

import org.springframework.stereotype.Service;

/**
 *@title : OrderServiceV3
 *@author : wikyubok 
 *@date : "2021-11-02 17:22:30"
 *@description : 프록시 패턴 orderService V3 auto componentscan
*/

@Service
public class OrderServiceV3 {
    
    private final OrderRepositoryV3 orderRepositoryV3;

    public OrderServiceV3(OrderRepositoryV3 orderRepositoryV3) {
        this.orderRepositoryV3 = orderRepositoryV3;
    }

    public void orderItem(String itemId) {

        orderRepositoryV3.save(itemId);

    }

}
