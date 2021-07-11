package com.upstox.domain;

import java.time.LocalDateTime;

/**
 * Domain class for Trade data
 * 
 * @author N.Wani
 *
 */
public class Trade {
  private String symbol;
  private double price;
  private double quantity;
  private LocalDateTime timestamp;

  // Variable used for internal purpose to communicate
  // completion of sending of trade data
  private boolean completed;

  public Trade() {
  }

  public Trade(String symbol, double price, double quantity, LocalDateTime timestamp) {
    super();
    this.symbol = symbol;
    this.price = price;
    this.quantity = quantity;
    this.timestamp = timestamp;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public double getQuantity() {
    return quantity;
  }

  public void setQuantity(double quantity) {
    this.quantity = quantity;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

}
