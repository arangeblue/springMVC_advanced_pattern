package com.proxy;

import com.proxy.config.v1_proxy.ConcreteProxyConfig;
import com.proxy.trace.logtrace.LogTrace;
import com.proxy.trace.logtrace.ThreadLocalLogTrace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;


// @Import(AppV1Config.class)
@Import(ConcreteProxyConfig.class)
@SpringBootApplication(scanBasePackages = "com.proxy.config.v1_proxy")
public class PatternApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatternApplication.class, args);
	}

	@Bean
	public LogTrace logTrace() {
		return new ThreadLocalLogTrace();
	}

}
