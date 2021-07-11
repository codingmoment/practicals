package com.upstox.server;

import static com.upstox.TradeTestData.OHLC_01;
import static com.upstox.TradeTestData.OHLC_02;
import static com.upstox.TradeTestData.OHLC_03;
import static com.upstox.TradeTestData.OHLC_04;
import static com.upstox.TradeTestData.TRADE_01;
import static com.upstox.TradeTestData.TRADE_02;
import static com.upstox.TradeTestData.TRADE_03;
import static com.upstox.TradeTestData.TRADE_04;
import static com.upstox.TradeTestData.TRADE_05;
import static com.upstox.TradeTestData.TRADE_06;
import static com.upstox.TradeTestData.TRADE_07;
import static com.upstox.TradeTestData.TRADE_08;
import static com.upstox.TradeTestData.TRADE_09;
import static com.upstox.TradeTestData.TRADE_10;
import static com.upstox.TradeTestData.TRADE_11;
import static com.upstox.TradeTestData.TRADE_12;
import static com.upstox.TradeTestData.TRADE_13;
import static com.upstox.TradeTestData.TRADE_14;
import static com.upstox.TradeTestData.TRADE_15;
import static com.upstox.TradeTestData.TRADE_16;
import static com.upstox.TradeTestData.TRADE_17;
import static com.upstox.TradeTestData.TRADE_18;
import static com.upstox.TradeTestData.TRADE_19;
import static com.upstox.TradeTestData.TRADE_20;
import static com.upstox.TradeTestData.TRADE_21;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.upstox.domain.OHLC;

public class OHLCEngineTest {

  private OHLCEngine ohlcEngine;

  @BeforeEach
  public void setUp() {
    ohlcEngine = new OHLCEngine();
  }

  @Test
  public void testSingleStockSingleBar() {
    ohlcEngine.computeOHLC(TRADE_01);
    ohlcEngine.computeOHLC(TRADE_02);
    ohlcEngine.computeOHLC(TRADE_03);
    ohlcEngine.computeOHLC(TRADE_04);
    ohlcEngine.computeOHLC(TRADE_05);
    OHLC ohlc = ohlcEngine.computeOHLC(TRADE_06);
    assertEquals(OHLC_01, ohlc);
  }

  @Test
  public void testSingleStockTwoConnsecutiveBars() {
    ohlcEngine.computeOHLC(TRADE_01);
    ohlcEngine.computeOHLC(TRADE_02);
    ohlcEngine.computeOHLC(TRADE_03);
    ohlcEngine.computeOHLC(TRADE_04);
    ohlcEngine.computeOHLC(TRADE_05);
    ohlcEngine.computeOHLC(TRADE_06);
    ohlcEngine.computeOHLC(TRADE_07);
    ohlcEngine.computeOHLC(TRADE_08);
    ohlcEngine.computeOHLC(TRADE_09);
    ohlcEngine.computeOHLC(TRADE_10);
    OHLC ohlc = ohlcEngine.computeOHLC(TRADE_11);
    assertEquals(OHLC_02, ohlc);
  }

  @Test
  public void testSingleStockTwoBarsWithMissingOneBar() {
    ohlcEngine.computeOHLC(TRADE_01);
    ohlcEngine.computeOHLC(TRADE_02);
    ohlcEngine.computeOHLC(TRADE_03);
    ohlcEngine.computeOHLC(TRADE_04);
    ohlcEngine.computeOHLC(TRADE_05);
    ohlcEngine.computeOHLC(TRADE_06);
    ohlcEngine.computeOHLC(TRADE_12);
    ohlcEngine.computeOHLC(TRADE_13);
    ohlcEngine.computeOHLC(TRADE_14);
    ohlcEngine.computeOHLC(TRADE_15);
    OHLC ohlc = ohlcEngine.computeOHLC(TRADE_16);
    assertEquals(OHLC_03, ohlc);
  }

  @Test
  public void testSingleStockTwoBarsWithMissingFiveBars() {
    ohlcEngine.computeOHLC(TRADE_01);
    ohlcEngine.computeOHLC(TRADE_02);
    ohlcEngine.computeOHLC(TRADE_03);
    ohlcEngine.computeOHLC(TRADE_04);
    ohlcEngine.computeOHLC(TRADE_05);
    ohlcEngine.computeOHLC(TRADE_06);
    ohlcEngine.computeOHLC(TRADE_17);
    ohlcEngine.computeOHLC(TRADE_18);
    ohlcEngine.computeOHLC(TRADE_19);
    ohlcEngine.computeOHLC(TRADE_20);
    OHLC ohlc = ohlcEngine.computeOHLC(TRADE_21);
    assertEquals(OHLC_04, ohlc);
  }
}
