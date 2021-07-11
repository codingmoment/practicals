package com.upstox;

import java.time.LocalDateTime;

import com.upstox.domain.OHLC;

public class OHLCTestData {
  private static final LocalDateTime TIMESTAMP = LocalDateTime.of(2020, 8, 1, 10, 0, 0);

  public static final OHLC OHLC_BAR_01_01 = new OHLC("ABC", 12.0, 12.0, 12.0, 12.0, 1.0, TIMESTAMP, 1);
  public static final OHLC OHLC_BAR_01_02 = new OHLC("ABC", 12.0, 14.0, 12.0, 14.0, 3.0, TIMESTAMP, 1);
  public static final OHLC OHLC_BAR_01_03 = new OHLC("ABC", 12.0, 16.0, 12.0, 16.0, 5.0, TIMESTAMP, 1);
  public static final OHLC OHLC_BAR_01_04 = new OHLC("ABC", 12.0, 16.0, 8.0, 8.0, 7.0, TIMESTAMP, 1);
  public static final OHLC OHLC_BAR_01_05 = new OHLC("ABC", 12.0, 16.0, 8.0, 12.0, 9.0, TIMESTAMP, 1);

  public static final OHLC OHLC_BAR_02_01 = new OHLC("ABC", 10.0, 10.0, 10.0, 10.0, 1.0, TIMESTAMP, 2);
  public static final OHLC OHLC_BAR_02_02 = new OHLC("ABC", 10.0, 14.0, 10.0, 14.0, 2.0, TIMESTAMP, 2);
  public static final OHLC OHLC_BAR_02_03 = new OHLC("ABC", 10.0, 14.0, 8.0, 8.0, 4.0, TIMESTAMP, 2);
  public static final OHLC OHLC_BAR_02_04 = new OHLC("ABC", 10.0, 14.0, 8.0, 9.0, 7.0, TIMESTAMP, 2);
  public static final OHLC OHLC_BAR_02_05 = new OHLC("ABC", 10.0, 14.0, 8.0, 12.0, 8.0, TIMESTAMP, 2);

  public static final OHLC OHLC_BAR_03_01 = new OHLC("ABC", 10.0, 10.0, 10.0, 10.0, 4.0, TIMESTAMP, 3);

  public static final OHLC OHLC_BAR_07_01 = new OHLC("ABC", 10.0, 10.0, 10.0, 10.0, 2.0, TIMESTAMP, 7);

  public static final OHLC OHLC_BAR_02_EMPTY = new OHLC("ABC", -1, -1, -1, -1, -1, TIMESTAMP, 2);
  public static final OHLC OHLC_BAR_03_EMPTY = new OHLC("ABC", -1, -1, -1, -1, -1, TIMESTAMP, 3);
  public static final OHLC OHLC_BAR_04_EMPTY = new OHLC("ABC", -1, -1, -1, -1, -1, TIMESTAMP, 4);
  public static final OHLC OHLC_BAR_05_EMPTY = new OHLC("ABC", -1, -1, -1, -1, -1, TIMESTAMP, 5);
  public static final OHLC OHLC_BAR_06_EMPTY = new OHLC("ABC", -1, -1, -1, -1, -1, TIMESTAMP, 6);

}
