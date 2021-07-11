package com.demo.account.metrics;

import java.math.BigDecimal;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.account.domain.Account;
import com.demo.account.service.AccountService;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;

public class TotalBalanceMetrics implements MeterBinder {

  @Autowired
  private AccountService accountService;

  private static final String GUAGE_NAME = "account";

  @Override
  public void bindTo(MeterRegistry registry) {
    Gauge.builder(GUAGE_NAME, this, value -> value.getTotalBalance()).description("Total Account Balance").baseUnit("total_balance").register(registry);
  }

  private Double getTotalBalance() {
    return accountService.getAllAccounts().stream().map(Account::getBalance).collect(Collectors.summingDouble(BigDecimal::doubleValue));
  }

}
