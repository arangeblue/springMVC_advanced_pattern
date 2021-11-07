package com.proxy;

import com.proxy.config.v2_dynamicproxy.DynamicProxyFilterConfig;
import com.proxy.trace.logtrace.LogTrace;
import com.proxy.trace.logtrace.ThreadLocalLogTrace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;


// @Import(AppV1Config.class)
@Import(DynamicProxyFilterConfig.class)
@SpringBootApplication(scanBasePackages = "com.proxy.app")
public class PatternApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatternApplication.class, args);
	}

	@Bean
	public LogTrace logTrace() {
		return new ThreadLocalLogTrace();
	}

}
