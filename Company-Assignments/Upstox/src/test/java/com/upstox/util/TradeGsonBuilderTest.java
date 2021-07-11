package com.upstox.util;

import static com.upstox.OHLCTestData.OHLC_BAR_02_EMPTY;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TradeGsonBuilderTest {

  @Test
  public void testOhlcGsonSerializerForEmptyBar() {
    String json = TradeGsonBuilder.OHLC_GSON.toJson(OHLC_BAR_02_EMPTY);
    assertEquals("{\"symbol\":\"ABC\",\"bar_num\":2}", json);
  }
}
