package com.proxy.config.v5_autoproxy;

import com.proxy.config.AppV1Config;
import com.proxy.config.AppV2Config;
import com.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import com.proxy.trace.logtrace.LogTrace;

import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *@title : AutoProxyConfig
 *@author : wikyubok 
 *@date : "2021-11-11 17:51:18"
 *@description : 스프링이 후처리기 기능을 제공해주는 것을 사용, 이미 후처리기가 등록되어 있기 때문에 advisor만 찾아오면 된다.
*/


@Import({AppV1Config.class, AppV2Config.class})
@Configuration
public class AutoProxyConfig {

    @Bean
    public Advisor advisor1(LogTrace logTrace) {
        
        //pointcut
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");

        //advice
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);

        return new DefaultPointcutAdvisor(pointcut, advice);

    }
    
}
