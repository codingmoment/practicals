package com.upstox.server;

import static com.upstox.util.TradeGsonBuilder.TRADE_GSON;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonSyntaxException;
import com.upstox.domain.Trade;

/**
 * This class reads data from "trades.json" file and sends the trade data to the queue.
 * @author N.Wani
 *
 */
public class TradeDataReader {

  private static final Logger LOGGER = LoggerFactory.getLogger(TradeDataReader.class);

  private final BlockingQueue<Trade> tradeQueue;

  public TradeDataReader(BlockingQueue<Trade> tradeQueue) {
    this.tradeQueue = tradeQueue;
  }

  public void readFileAndPrintLines() {
    ClassLoader classLoader = getClass().getClassLoader();

    try (FileReader reader = new FileReader(classLoader.getResource("trades.json").getFile()); BufferedReader bufferedReader = new BufferedReader(reader)) {

      String line;
      while ((line = bufferedReader.readLine()) != null) {
        tradeQueue.put(TRADE_GSON.fromJson(line, Trade.class));
      }
      Trade completionTrade = new Trade();
      completionTrade.setCompleted(true);
      tradeQueue.put(completionTrade);
    }
    catch (FileNotFoundException e) {
      LOGGER.error(e.getMessage(), e);
    }
    catch (IOException e) {
      LOGGER.error(e.getMessage(), e);
    }
    catch (JsonSyntaxException e) {
      LOGGER.error(e.getMessage(), e);
    }
    catch (InterruptedException e) {
      LOGGER.error(e.getMessage(), e);
    }
  }

}
