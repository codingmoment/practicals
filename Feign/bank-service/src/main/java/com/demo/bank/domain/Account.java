package com.demo.bank.domain;

import java.math.BigDecimal;

import com.demo.bank.config.FeignProperty;

public class Account {

  private Long accountId;
  @FeignProperty("accountHolderName")
  private String customerName;
  private BigDecimal balance;

  public Long getAccountId() {
    return accountId;
  }

  public void setAccountId(Long accountId) {
    this.accountId = accountId;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

}
