package com.proxy.pattern;

import com.proxy.pattern.proxy.code.CacheProxy;
import com.proxy.pattern.proxy.code.ProxyPatternClient;
import com.proxy.pattern.proxy.code.RealSubject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {

    @Test
    @DisplayName("프록시 패턴 테스트, 프록시를 사용하지 않고 진짜 객체를 사용했을 경우")
    public void noProxyTest() {

        RealSubject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(realSubject);

        client.execute();
        client.execute();
        client.execute();
    }
    

    @Test
    @DisplayName("프록시 패턴 테스트, 캐시를 이용한 프록시 테스트")
    public void cacheProxyTest() {
        
        RealSubject realSubject = new RealSubject();
        CacheProxy cacheProxy = new CacheProxy(realSubject);
        ProxyPatternClient client = new ProxyPatternClient(cacheProxy);
    
        client.execute();
        client.execute();
        client.execute();


    
    }

}


