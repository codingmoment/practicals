package com.demo.account.metrics;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class ActuatorConfig {

  @Bean
  public TotalBalanceMetrics totalBalanceMetrics() {
    return new TotalBalanceMetrics();
  }
}
