package com.upstox.messaging;

import static com.upstox.util.Constants.QUEUE_CAPACITY;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import com.upstox.domain.OHLC;
import com.upstox.domain.Trade;

/**
 * Queue for messaging between threads.
 * 
 * @author N.Wani
 *
 */
public class Queue {
  public static final BlockingQueue<Trade> TRADE_QUEUE = new LinkedBlockingDeque<Trade>(QUEUE_CAPACITY);
  public static final BlockingQueue<OHLC> OHLC_QUEUE = new LinkedBlockingDeque<OHLC>(QUEUE_CAPACITY);
}
