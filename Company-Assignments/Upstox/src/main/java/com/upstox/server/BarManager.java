package com.upstox.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.upstox.domain.OHLC;
import com.upstox.domain.OHLCMode;

/**
 * This class manages generation of OHLC data for the subscribed clients.
 * 
 * @author N.Wani
 *
 */
public class BarManager {
  private Map<String, OHLC> stockOhlcMap = new HashMap<>();
  private OHLCMode ohlcMode;
  private static BarManager barManager;

  private BarManager(OHLCMode ohlcMode) {
    this.ohlcMode = ohlcMode;
  }

  public static void initialize(OHLCMode ohlcMode) {
    barManager = new BarManager(ohlcMode);
  }

  public static BarManager getInstance() {
    return barManager;
  }

  /**
   * This method creates OHLC bars.
   * 
   * @param ohlc OHLC packet
   * @return List of OHLC bars
   */
  public List<OHLC> getOhlcBars(OHLC ohlc) {
    List<OHLC> ohlcList = new ArrayList<>();

    if (ohlcMode == OHLCMode.BY_BAR) {
      if (stockOhlcMap.containsKey(ohlc.getSymbol())) {
        OHLC prevOHLC = stockOhlcMap.get(ohlc.getSymbol());
        if (prevOHLC.getBarNumber() != ohlc.getBarNumber()) {
          ohlcList.add(prevOHLC);
          // Add empty bars in the list
          if (ohlc.getBarNumber() - prevOHLC.getBarNumber() > 1) {
            for (int i = 1; i < ohlc.getBarNumber() - prevOHLC.getBarNumber(); i++) {
              ohlcList.add(new OHLC(ohlc.getSymbol(), -1, -1, -1, -1, -1, null, prevOHLC.getBarNumber() + i));
            }
          }
        }
      }
    }
    else {
      ohlcList.add(ohlc);
      if (stockOhlcMap.containsKey(ohlc.getSymbol())) {
        OHLC prevOHLC = stockOhlcMap.get(ohlc.getSymbol());
        // Add empty bars in the list
        if (ohlc.getBarNumber() - prevOHLC.getBarNumber() > 1) {
          for (int i = 1; i < ohlc.getBarNumber() - prevOHLC.getBarNumber(); i++) {
            ohlcList.add(new OHLC(ohlc.getSymbol(), -1, -1, -1, -1, -1, null, prevOHLC.getBarNumber() + i));
          }
        }
      }
    }

    stockOhlcMap.put(ohlc.getSymbol(), ohlc);
    return ohlcList;
  }

  public List<OHLC> getPendingOhlcBars() {
    return stockOhlcMap.values().stream().collect(Collectors.toList());
  }

}
