package com.demo.httpcaching.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    WebContentInterceptor interceptor = new WebContentInterceptor();
    //@formatter:off
    interceptor.addCacheMapping(CacheControl.maxAge(60, TimeUnit.SECONDS)
                                            .noTransform()
                                            .mustRevalidate(), "/**/files/*");
    //@formatter:on    
    registry.addInterceptor(interceptor);
  }
}
