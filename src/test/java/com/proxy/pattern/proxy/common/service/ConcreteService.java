package com.proxy.pattern.proxy.common.service;

import lombok.extern.slf4j.Slf4j;


/**
 *@title : ConcreteService
 *@author : wikyubok 
 *@date : "2021-11-05 16:26:15"
 *@description : interface가 없는 구체 클래스
*/

@Slf4j
public class ConcreteService {

    public void call() {
        log.info("ConcreteService 호출");
    }
    
}
