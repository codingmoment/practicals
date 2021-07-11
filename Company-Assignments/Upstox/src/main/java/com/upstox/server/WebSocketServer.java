package com.upstox.server;

import static com.upstox.messaging.Queue.OHLC_QUEUE;
import static com.upstox.util.TradeGsonBuilder.OHLC_GSON;

import java.io.IOException;
import java.util.List;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.upstox.domain.OHLC;
import com.upstox.registry.SessionRegistry;
import com.upstox.registry.Subscription;
import com.upstox.util.OHLCEncoder;

/**
 * This is the websocket server that broadcast the OHLC data to the subscribed clients.
 * 
 * @author N.Wani
 *
 */
@ServerEndpoint(value = "/{symbol}", encoders = OHLCEncoder.class)
public class WebSocketServer {

  private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketServer.class);

  @OnOpen
  public void onOpen(Session session, @PathParam("symbol") String symbol) throws IOException, EncodeException {
    LOGGER.info("Got new subscription for symbol - {}", symbol);
    Subscription subscription = new Subscription();
    subscription.setWebSocketSession(session);
    subscription.setEndpoint(this);
    subscription.setSessionId(session.getId());
    subscription.setSymbol(symbol);
    SessionRegistry.addSubscription(subscription);
  }

  @OnMessage
  public void onMessage(Session session, String message) throws IOException, EncodeException {
    LOGGER.info("Received message - {}", message);
  }

  @OnClose
  public void onClose(Session session) throws IOException, EncodeException {
    LOGGER.info("Subscription closed for sessionId - {}", session.getId());
    SessionRegistry.removeSubscriptionForSession(session.getId());
  }

  @OnError
  public void onError(Session session, Throwable throwable) {
    LOGGER.error(throwable.getMessage(), throwable);
  }

  public static void broadcastTradeData() throws IOException, EncodeException {
    try {
      while (true) {

        OHLC ohlc = OHLC_QUEUE.take();

        List<OHLC> ohlcBars = ohlc.isCompleted() ? BarManager.getInstance().getPendingOhlcBars() : BarManager.getInstance().getOhlcBars(ohlc);

        ohlcBars.forEach(bar -> {
          LOGGER.info("SENDING - {}", OHLC_GSON.toJson(bar));

          SessionRegistry.getSubscriptions().forEach(subscription -> {
            synchronized (subscription) {
              try {
                if (subscription.getSymbol() != null && subscription.getSymbol().equals(bar.getSymbol())) {
                  subscription.getWebSocketSession().getBasicRemote().sendObject(bar);
                }
              }
              catch (IOException | EncodeException e) {
                LOGGER.error(e.getMessage(), e);
              }
            }
          });
        });
      }
    }
    catch (InterruptedException e) {
      LOGGER.error(e.getMessage(), e);
    }

  }
}
