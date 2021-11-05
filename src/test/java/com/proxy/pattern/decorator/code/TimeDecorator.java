package com.proxy.pattern.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeDecorator implements Component {
    
    private Component component;


    public TimeDecorator(Component component) {
        this.component = component;
    }


    @Override
    public String operation() {
        log.info("TimeDecolator 실행");
        long startTime = System.currentTimeMillis();

        String result = component.operation();
        
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("TimeDecolator 종료 resultTime = {}ms", resultTime);
        return result;
    }
    
}
