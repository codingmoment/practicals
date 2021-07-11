package com.upstox.server;

import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.upstox.domain.OHLC;
import com.upstox.domain.Trade;

/**
 * This class listens to trade data and creates OHLC packets.
 * 
 * @author N.Wani
 *
 */
public class FiniteStateMachine {
  private static final Logger LOGGER = LoggerFactory.getLogger(FiniteStateMachine.class);

  private final BlockingQueue<Trade> tradeQueue;
  private final BlockingQueue<OHLC> ohlcQueue;
  private final OHLCEngine ohlcEngine;

  public FiniteStateMachine(BlockingQueue<Trade> tradeQueue, BlockingQueue<OHLC> ohlcQueue, OHLCEngine ohlcEngine) {
    this.tradeQueue = tradeQueue;
    this.ohlcQueue = ohlcQueue;
    this.ohlcEngine = ohlcEngine;
  }

  public void computeOHLC() {
    try {
      while (true) {
        Trade trade = tradeQueue.take();
        if (trade.isCompleted()) {
          OHLC completedOHLC = new OHLC();
          completedOHLC.setCompleted(true);
          ohlcQueue.put(completedOHLC);
          return;
        }
        OHLC ohlc = ohlcEngine.computeOHLC(trade);
        ohlcQueue.put(ohlc);
      }
    }
    catch (InterruptedException e) {
      LOGGER.error(e.getMessage(), e);
    }
  }

}
