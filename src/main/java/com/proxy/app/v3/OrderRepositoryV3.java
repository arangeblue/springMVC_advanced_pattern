package com.proxy.app.v3;

import org.springframework.stereotype.Repository;


/**
 *@title : OrderRepositoryV3
 *@author : wikyubok 
 *@date : "2021-11-02 17:21:34"
 *@description : 프록시 orderRepository v3 auto componentscan
*/

@Repository
public class OrderRepositoryV3 {
    
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
