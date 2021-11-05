package com.proxy.app.v2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

/**
 *@title : OrderControllerV2
 *@author : wikyubok 
 *@date : "2021-11-02 16:45:02"
 *@description : 프록시 패턴 OrderController V2 구현체
*/


@Slf4j
@RequestMapping
@ResponseBody
public class OrderControllerV2 {
    
    private final OrderServiceV2 orderServiceV2;


    public OrderControllerV2(OrderServiceV2 orderServiceV2) {
        this.orderServiceV2 = orderServiceV2;
    }


    @GetMapping("/v2/request")
    public String request(String itemId) {
        orderServiceV2.orderItem(itemId);
        return "ok";
    }


    @GetMapping("/v2/no-log")    
    public String noLog() {
        // TODO Auto-generated method stub
        return "ok";
    }

}
