package com.devmango.spring.management.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ManagementConfig {

	@Bean
	public ExecutorService cacheExecutorService() {
		return Executors.newCachedThreadPool();
	}

	@Bean
	public ExecutorService fixedExecutorService() {
		return Executors.newFixedThreadPool(2);
	}

}
