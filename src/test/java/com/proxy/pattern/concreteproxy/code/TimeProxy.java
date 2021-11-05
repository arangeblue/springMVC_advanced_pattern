package com.proxy.pattern.concreteproxy.code;

import lombok.extern.slf4j.Slf4j;

/**
 *@title : TimeProxy
 *@author : wikyubok 
 *@date : "2021-11-04 16:26:33"
 *@description : 시간 측정하는 프록시, ConcreteLogic을 상속받는다. 즉 concreteLogic의 자식관계가 된다.
*/

@Slf4j
public class TimeProxy extends ConcreteLogic {
    
    private ConcreteLogic concreteLogic;

    public TimeProxy(ConcreteLogic concreteLogic) {
        this.concreteLogic = concreteLogic;
    }

    @Override
    public String operation() {
        
        log.info("TimeDecorator 실행");

        long startTime = System.currentTimeMillis();

        // 로직 실행
        String result = concreteLogic.operation();
    
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;

        log.info("TimeDecorator 종료, resultTime = {}ms", resultTime);
        return result;
    }


    


}
