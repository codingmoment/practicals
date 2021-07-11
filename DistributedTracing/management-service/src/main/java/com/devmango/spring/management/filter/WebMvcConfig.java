package com.devmango.spring.management.filter;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	private final HeaderInterceptor headerInterceptor;

	@Autowired
	public WebMvcConfig(HeaderInterceptor headerInterceptor) {
		this.headerInterceptor = headerInterceptor;
	}

	@Override
	public void addInterceptors(@NotNull InterceptorRegistry registry) {
		registry.addInterceptor(headerInterceptor);
	}
}