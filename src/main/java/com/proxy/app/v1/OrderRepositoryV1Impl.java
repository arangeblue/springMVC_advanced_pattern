package com.proxy.app.v1;

/**
 * @title : OrderRepositoryV1Impl
 * @author : wikyubok
 * @date : "2021-11-02 16:37:56"
 * @description : 프록시 패턴 v1 orderRepository 구현체
 */

public class OrderRepositoryV1Impl implements OrderRepositoryV1 {

    @Override
    public void save(String itemId) {
        

        if (itemId.equals("ex")) {
            throw new IllegalArgumentException("예외 발생!!");
        }

        sleep(1000);
    }

    private void sleep(int i) {
        
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
