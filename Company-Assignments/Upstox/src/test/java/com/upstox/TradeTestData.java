package com.upstox;

import java.time.LocalDateTime;

import com.upstox.domain.OHLC;
import com.upstox.domain.Trade;

public class TradeTestData {
  private static final LocalDateTime TIMESTAMP = LocalDateTime.of(2020, 8, 1, 10, 0, 0);

  public static final Trade TRADE_01 = new Trade("ABC", 10.0, 2.0, TIMESTAMP);
  public static final Trade TRADE_02 = new Trade("ABC", 12.0, 1.0, TIMESTAMP.plusSeconds(1));
  public static final Trade TRADE_03 = new Trade("ABC", 14.0, 10.0, TIMESTAMP.plusSeconds(2));
  public static final Trade TRADE_04 = new Trade("ABC", 16.0, 8.0, TIMESTAMP.plusSeconds(3));
  public static final Trade TRADE_05 = new Trade("ABC", 8.0, 7.0, TIMESTAMP.plusSeconds(4));
  public static final Trade TRADE_06 = new Trade("ABC", 12.0, 7.0, TIMESTAMP.plusSeconds(5));

  public static final OHLC OHLC_01 = new OHLC("ABC", 10.0, 16.0, 8.0, 12.0, 35.0, TIMESTAMP, 1);
  
  public static final Trade TRADE_07 = new Trade("ABC", 12.0, 1.0, TIMESTAMP.plusSeconds(16));
  public static final Trade TRADE_08 = new Trade("ABC", 14.0, 10.0, TIMESTAMP.plusSeconds(17));
  public static final Trade TRADE_09 = new Trade("ABC", 20.0, 8.0, TIMESTAMP.plusSeconds(18));
  public static final Trade TRADE_10 = new Trade("ABC", 10.0, 7.0, TIMESTAMP.plusSeconds(19));
  public static final Trade TRADE_11 = new Trade("ABC", 16.0, 7.0, TIMESTAMP.plusSeconds(20));
  
  public static final OHLC OHLC_02 = new OHLC("ABC", 12.0, 20.0, 10.0, 16.0, 33.0, TIMESTAMP.plusSeconds(16), 2);

  public static final Trade TRADE_12 = new Trade("ABC", 12.0, 1.0, TIMESTAMP.plusSeconds(31));
  public static final Trade TRADE_13 = new Trade("ABC", 14.0, 10.0, TIMESTAMP.plusSeconds(32));
  public static final Trade TRADE_14 = new Trade("ABC", 20.0, 8.0, TIMESTAMP.plusSeconds(33));
  public static final Trade TRADE_15 = new Trade("ABC", 10.0, 7.0, TIMESTAMP.plusSeconds(34));
  public static final Trade TRADE_16 = new Trade("ABC", 16.0, 7.0, TIMESTAMP.plusSeconds(35));
  
  public static final OHLC OHLC_03 = new OHLC("ABC", 12.0, 20.0, 10.0, 16.0, 33.0, TIMESTAMP.plusSeconds(31), 3);

  public static final Trade TRADE_17 = new Trade("ABC", 12.0, 1.0, TIMESTAMP.plusSeconds(91));
  public static final Trade TRADE_18 = new Trade("ABC", 14.0, 10.0, TIMESTAMP.plusSeconds(92));
  public static final Trade TRADE_19 = new Trade("ABC", 20.0, 8.0, TIMESTAMP.plusSeconds(93));
  public static final Trade TRADE_20 = new Trade("ABC", 10.0, 7.0, TIMESTAMP.plusSeconds(94));
  public static final Trade TRADE_21 = new Trade("ABC", 16.0, 7.0, TIMESTAMP.plusSeconds(95));
  
  public static final OHLC OHLC_04 = new OHLC("ABC", 12.0, 20.0, 10.0, 16.0, 33.0, TIMESTAMP.plusSeconds(91), 7);
}
