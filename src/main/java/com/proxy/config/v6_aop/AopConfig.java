package com.proxy.config.v6_aop;

import com.proxy.config.AppV1Config;
import com.proxy.config.AppV2Config;
import com.proxy.config.v6_aop.aspect.LogTraceAspect;
import com.proxy.trace.logtrace.LogTrace;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Configuration
@Import({
        AppV1Config.class,
        AppV2Config.class
})
public class AopConfig {

    @Bean
    public LogTraceAspect logTraceAspect(LogTrace logTrace) {
        return new LogTraceAspect(logTrace);
    }
    
    

}
