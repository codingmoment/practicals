package com.upstox.server;

import static com.upstox.OHLCTestData.OHLC_BAR_01_01;
import static com.upstox.OHLCTestData.OHLC_BAR_01_02;
import static com.upstox.OHLCTestData.OHLC_BAR_01_03;
import static com.upstox.OHLCTestData.OHLC_BAR_01_04;
import static com.upstox.OHLCTestData.OHLC_BAR_01_05;
import static com.upstox.OHLCTestData.OHLC_BAR_02_01;
import static com.upstox.OHLCTestData.OHLC_BAR_02_EMPTY;
import static com.upstox.OHLCTestData.OHLC_BAR_03_01;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.upstox.domain.OHLC;
import com.upstox.domain.OHLCMode;

public class BarManagerByBarTest {

  private BarManager barManager;

  @BeforeEach
  public void setUp() {
    BarManager.initialize(OHLCMode.BY_BAR);
    barManager = BarManager.getInstance();
  }

  @Test
  public void testOhlcNoBar() {
    List<OHLC> ohlcBars = barManager.getOhlcBars(OHLC_BAR_01_01);
    assertEquals(0, ohlcBars.size());
    ohlcBars = barManager.getOhlcBars(OHLC_BAR_01_02);
    assertEquals(0, ohlcBars.size());
    ohlcBars = barManager.getOhlcBars(OHLC_BAR_01_03);
    assertEquals(0, ohlcBars.size());
    ohlcBars = barManager.getOhlcBars(OHLC_BAR_01_04);
    assertEquals(0, ohlcBars.size());
    ohlcBars = barManager.getOhlcBars(OHLC_BAR_01_05);
    assertEquals(0, ohlcBars.size());
  }

  @Test
  public void testOhlcOneBar() {
    barManager.getOhlcBars(OHLC_BAR_01_05);
    List<OHLC> ohlcBars = barManager.getOhlcBars(OHLC_BAR_02_01);
    assertEquals(1, ohlcBars.size());
    assertEquals(OHLC_BAR_01_05, ohlcBars.get(0));
  }

  @Test
  public void testOhlcTwoBarsWithMissingOneBar() {
    barManager.getOhlcBars(OHLC_BAR_01_05);
    List<OHLC> ohlcBars = barManager.getOhlcBars(OHLC_BAR_03_01);
    assertEquals(2, ohlcBars.size());
    assertEquals(OHLC_BAR_01_05, ohlcBars.get(0));
    assertEquals(OHLC_BAR_02_EMPTY, ohlcBars.get(1));
  }

}
