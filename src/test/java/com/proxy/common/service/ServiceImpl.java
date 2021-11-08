package com.proxy.common.service;

import org.apache.catalina.Service;

import lombok.extern.slf4j.Slf4j;

/**
 *@title : ServiceImpl
 *@author : wikyubok 
 *@date : "2021-11-05 16:25:18"
 *@description : ServiceInterface에 대한 구체 클래스
*/

@Slf4j
public class ServiceImpl implements ServiceInterface {

    @Override
    public void save() {
        log.info("save 호출");
    }

    @Override
    public void find() {
        log.info("find 호출");
    }
    
}
