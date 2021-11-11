package com.proxy;

import com.proxy.config.v3_proxyfactory.ProxyFactoryConfigV2;
import com.proxy.config.v4_postprocessor.BeanPostProcessorConfig;
import com.proxy.config.v5_autoproxy.AutoProxyConfig;
import com.proxy.trace.logtrace.LogTrace;
import com.proxy.trace.logtrace.ThreadLocalLogTrace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;


// @Import(AppV1Config.class)
// @Import(DynamicProxyFilterConfig.class)
// @Import(ProxyFactoryConfigV2.class)
// @Import(BeanPostProcessorConfig.class)
@Import(AutoProxyConfig.class)
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
