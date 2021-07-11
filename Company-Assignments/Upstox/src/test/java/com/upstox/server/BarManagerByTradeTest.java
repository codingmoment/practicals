package com.upstox.server;

import static com.upstox.OHLCTestData.OHLC_BAR_01_01;
import static com.upstox.OHLCTestData.OHLC_BAR_01_02;
import static com.upstox.OHLCTestData.OHLC_BAR_01_03;
import static com.upstox.OHLCTestData.OHLC_BAR_01_04;
import static com.upstox.OHLCTestData.OHLC_BAR_01_05;
import static com.upstox.OHLCTestData.OHLC_BAR_02_01;
import static com.upstox.OHLCTestData.OHLC_BAR_02_02;
import static com.upstox.OHLCTestData.OHLC_BAR_02_03;
import static com.upstox.OHLCTestData.OHLC_BAR_02_04;
import static com.upstox.OHLCTestData.OHLC_BAR_02_05;
import static com.upstox.OHLCTestData.OHLC_BAR_02_EMPTY;
import static com.upstox.OHLCTestData.OHLC_BAR_03_01;
import static com.upstox.OHLCTestData.OHLC_BAR_03_EMPTY;
import static com.upstox.OHLCTestData.OHLC_BAR_04_EMPTY;
import static com.upstox.OHLCTestData.OHLC_BAR_05_EMPTY;
import static com.upstox.OHLCTestData.OHLC_BAR_06_EMPTY;
import static com.upstox.OHLCTestData.OHLC_BAR_07_01;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.upstox.domain.OHLC;
import com.upstox.domain.OHLCMode;

public class BarManagerByTradeTest {

  private BarManager barManager;

  @BeforeEach
  public void setUp() {
    BarManager.initialize(OHLCMode.BY_TRADE);
    barManager = BarManager.getInstance();
  }

  @Test
  public void testOhlcSingleBar() {
    barManager.getOhlcBars(OHLC_BAR_01_01);
    barManager.getOhlcBars(OHLC_BAR_01_02);
    barManager.getOhlcBars(OHLC_BAR_01_03);
    barManager.getOhlcBars(OHLC_BAR_01_04);
    List<OHLC> ohlcBars = barManager.getOhlcBars(OHLC_BAR_01_05);
    assertEquals(1, ohlcBars.size());
    assertEquals(OHLC_BAR_01_05, ohlcBars.get(0));
  }

  @Test
  public void testOhlcTwoBarsWithNoMissingBar() {
    barManager.getOhlcBars(OHLC_BAR_01_01);
    barManager.getOhlcBars(OHLC_BAR_01_02);
    barManager.getOhlcBars(OHLC_BAR_01_03);
    barManager.getOhlcBars(OHLC_BAR_01_04);
    barManager.getOhlcBars(OHLC_BAR_01_05);
    barManager.getOhlcBars(OHLC_BAR_02_01);
    barManager.getOhlcBars(OHLC_BAR_02_02);
    barManager.getOhlcBars(OHLC_BAR_02_03);
    barManager.getOhlcBars(OHLC_BAR_02_04);
    List<OHLC> ohlcBars = barManager.getOhlcBars(OHLC_BAR_02_05);
    assertEquals(1, ohlcBars.size());
    assertEquals(OHLC_BAR_02_05, ohlcBars.get(0));
  }

  @Test
  public void testOhlcTwoBarsWithMissingOneBar() {
    barManager.getOhlcBars(OHLC_BAR_01_01);
    barManager.getOhlcBars(OHLC_BAR_01_02);
    barManager.getOhlcBars(OHLC_BAR_01_03);
    barManager.getOhlcBars(OHLC_BAR_01_04);
    barManager.getOhlcBars(OHLC_BAR_01_05);
    List<OHLC> ohlcBars = barManager.getOhlcBars(OHLC_BAR_03_01);
    assertEquals(2, ohlcBars.size());
    assertEquals(OHLC_BAR_03_01, ohlcBars.get(0));
    assertEquals(OHLC_BAR_02_EMPTY, ohlcBars.get(1));
  }

  @Test
  public void testOhlcTwoBarsWithMissingFiveBars() {
    barManager.getOhlcBars(OHLC_BAR_01_01);
    barManager.getOhlcBars(OHLC_BAR_01_02);
    barManager.getOhlcBars(OHLC_BAR_01_03);
    barManager.getOhlcBars(OHLC_BAR_01_04);
    barManager.getOhlcBars(OHLC_BAR_01_05);
    List<OHLC> ohlcBars = barManager.getOhlcBars(OHLC_BAR_07_01);
    assertEquals(6, ohlcBars.size());
    assertEquals(OHLC_BAR_07_01, ohlcBars.get(0));
    assertEquals(OHLC_BAR_02_EMPTY, ohlcBars.get(1));
    assertEquals(OHLC_BAR_03_EMPTY, ohlcBars.get(2));
    assertEquals(OHLC_BAR_04_EMPTY, ohlcBars.get(3));
    assertEquals(OHLC_BAR_05_EMPTY, ohlcBars.get(4));
    assertEquals(OHLC_BAR_06_EMPTY, ohlcBars.get(5));
  }

}
