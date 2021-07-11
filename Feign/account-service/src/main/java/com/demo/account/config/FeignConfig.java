package com.demo.account.config;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import feign.codec.Decoder;

@Configuration
public class FeignConfig {

  private final ObjectFactory<HttpMessageConverters> messageConverters;
  private final ObjectMapper objectMapper;

  @Autowired
  public FeignConfig(ObjectFactory<HttpMessageConverters> messageConverters, ObjectMapper objectMapper) {
    this.messageConverters = messageConverters;
    this.objectMapper = objectMapper;
  }

  /**
   * Bean for the decoder used to translate responses to pages
   *
   * @return a bean for the feign Decoder
   */
  @Bean
  public Decoder feignDecoder() {
    return new ResponseEntityDecoder(new FeignDecoder(objectMapper, messageConverters));
  }

}
