package com.upstox.server;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import com.upstox.domain.OHLC;
import com.upstox.domain.Trade;

/**
 * This class holds the logic for generating OHLC data.
 * 
 * @author N.Wani
 *
 */
public class OHLCEngine {

  private Map<String, OHLC> stockOhlcMap = new HashMap<String, OHLC>();

  /**
   * This method creates OHLC packet based on received trade data.
   * 
   * @param trade Trade data
   * @return OHLC packet
   */
  public OHLC computeOHLC(Trade trade) {
    String symbol = trade.getSymbol();
    if (stockOhlcMap.containsKey(symbol)) {
      OHLC ohlc = stockOhlcMap.get(symbol);
      OHLC newOhlc = new OHLC();

      newOhlc.setSymbol(ohlc.getSymbol());

      if (ChronoUnit.SECONDS.between(ohlc.getTimestamp(), trade.getTimestamp()) >= 15) {
        newOhlc.setOpen(trade.getPrice());
        newOhlc.setHigh(trade.getPrice());
        newOhlc.setLow(trade.getPrice());
        newOhlc.setClose(trade.getPrice());
        newOhlc.setVolume(trade.getQuantity());
        newOhlc.setTimestamp(trade.getTimestamp());

        long barGap = (ChronoUnit.SECONDS.between(ohlc.getTimestamp(), trade.getTimestamp()) / 15);
        newOhlc.setBarNumber(ohlc.getBarNumber() + barGap);
      }
      else {
        newOhlc.setOpen(ohlc.getOpen());
        newOhlc.setHigh(ohlc.getHigh() < trade.getPrice() ? trade.getPrice() : ohlc.getHigh());
        newOhlc.setLow(ohlc.getLow() > trade.getPrice() ? trade.getPrice() : ohlc.getLow());
        newOhlc.setClose(trade.getPrice());
        newOhlc.setVolume(BigDecimal.valueOf(trade.getQuantity()).add(BigDecimal.valueOf(ohlc.getVolume())).doubleValue());
        newOhlc.setTimestamp(ohlc.getTimestamp());
        newOhlc.setBarNumber(ohlc.getBarNumber());
      }

      stockOhlcMap.put(symbol, newOhlc);
      return newOhlc;
    }
    else {
      OHLC ohlc = new OHLC(symbol, trade.getPrice(), trade.getPrice(), trade.getPrice(), trade.getPrice(), trade.getQuantity(), trade.getTimestamp(), 1);
      stockOhlcMap.put(symbol, ohlc);
      return ohlc;
    }
  }
}
