package com.proxy.app.v2;

/**
 *@title : OrderRepositoryV2Impl
 *@author : wikyubok 
 *@date : "2021-11-02 16:41:51"
 *@description : 프록시 패턴 OrderRepository V2 구현체
*/

public class OrderRepositoryV2{

    
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
