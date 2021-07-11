package com.upstox.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Domain class for OHLC
 * 
 * @author N.Wani
 *
 */
public class OHLC implements Serializable {
  private static final long serialVersionUID = 1L;

  private String symbol;
  private double open;
  private double high;
  private double low;
  private double close;
  private double volume;
  private LocalDateTime timestamp;
  private long barNumber;

  // Variable used for internal purpose to communicate
  // completion of sending of OHLC bars/packets
  private boolean isCompleted;

  public OHLC() {
  }

  public OHLC(String symbol, double open, double high, double low, double close, double volume, LocalDateTime timestamp, long barNumber) {
    super();
    this.symbol = symbol;
    this.open = open;
    this.high = high;
    this.low = low;
    this.close = close;
    this.volume = volume;
    this.timestamp = timestamp;
    this.barNumber = barNumber;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public double getOpen() {
    return open;
  }

  public void setOpen(double open) {
    this.open = open;
  }

  public double getHigh() {
    return high;
  }

  public void setHigh(double high) {
    this.high = high;
  }

  public double getLow() {
    return low;
  }

  public void setLow(double low) {
    this.low = low;
  }

  public double getClose() {
    return close;
  }

  public void setClose(double close) {
    this.close = close;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public long getBarNumber() {
    return barNumber;
  }

  public void setBarNumber(long barNumber) {
    this.barNumber = barNumber;
  }

  public boolean isCompleted() {
    return isCompleted;
  }

  public void setCompleted(boolean isCompleted) {
    this.isCompleted = isCompleted;
  }

  public double getVolume() {
    return volume;
  }

  public void setVolume(double volume) {
    this.volume = volume;
  }

  @Override
  public boolean equals(Object obj) {
    OHLC ohlc = (OHLC) obj;
    return this.open == ohlc.open && this.high == ohlc.high && this.low == ohlc.low && this.close == ohlc.close && this.barNumber == ohlc.barNumber && this.volume == ohlc.volume;
  }
}
