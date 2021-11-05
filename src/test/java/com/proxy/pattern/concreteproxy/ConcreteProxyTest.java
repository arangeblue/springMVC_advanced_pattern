package com.proxy.pattern.concreteproxy;

import com.proxy.pattern.concreteproxy.code.ConcreteClient;
import com.proxy.pattern.concreteproxy.code.ConcreteLogic;
import com.proxy.pattern.concreteproxy.code.TimeProxy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;


/**
 *@title : ConcreteProxyTest
 *@author : wikyubok 
 *@date : "2021-11-04 16:17:49"
 *@description : 구체 클래스 기반 프록시 테스트 
*/

@Slf4j
public class ConcreteProxyTest {
    

    @Test
    @DisplayName("구체 클래스 기반 프록시 테스트, 프록시 적용하지 않고 테스트")
    public void noProxy() {

        ConcreteLogic concreteLogic = new ConcreteLogic();
        ConcreteClient client = new ConcreteClient(concreteLogic);
        client.execute();

    }
    
    @Test
    @DisplayName(" 구체 클래스 기반 프록시 테스트, 프록시를 적용하여 테스트")
    public void addProxy() {
    
        ConcreteLogic concreteLogic = new ConcreteLogic();
        TimeProxy timeProxy = new TimeProxy(concreteLogic);
        ConcreteClient client = new ConcreteClient(timeProxy); // client에 있는 ConcreteLogic이 부모이고 그 자식인 timeProxy도 받을 수 있다.
        client.execute();
    
    }

}
