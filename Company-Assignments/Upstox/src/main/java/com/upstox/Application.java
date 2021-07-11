package com.upstox;

import static com.upstox.messaging.Queue.TRADE_QUEUE;
import static com.upstox.messaging.Queue.OHLC_QUEUE;
import static com.upstox.util.Constants.HOST;
import static com.upstox.util.Constants.PORT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.websocket.DeploymentException;
import javax.websocket.EncodeException;

import org.glassfish.tyrus.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.upstox.domain.OHLCMode;
import com.upstox.server.BarManager;
import com.upstox.server.FiniteStateMachine;
import com.upstox.server.OHLCEngine;
import com.upstox.server.TradeDataReader;
import com.upstox.server.WebSocketServer;;

/**
 * Main class.
 * 
 * @author N.Wani
 *
 */
public class Application {

  private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    try {
      Application application = new Application();
      
      // You can specify the mode here.
      // Mode is either OHLCMode.BY_BAR or OHLCMode.BY_TRADE
      BarManager.initialize(OHLCMode.BY_BAR);
      
      LOGGER.info("Starting WebSocketServer...");
      Server server = application.startWebSocketServer();

      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      LOGGER.info("Please press a key to start OHLP server.");
      reader.readLine();

      LOGGER.info("Starting TradeDataReader...");
      application.startTradeDataReader();

      LOGGER.info("Starting FiniteStateMachine...");
      application.startFiniteStateMachine();

      LOGGER.info("Please press a key to stop the server.");
      reader.readLine();
      server.stop();
    }
    catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
    }
  }

  private Server startWebSocketServer() throws DeploymentException {
    Server server = new Server(HOST, PORT, "/", null, WebSocketServer.class);
    server.start();

    Runnable webSocketServer = new Runnable() {
      @Override
      public void run() {
        try {
          WebSocketServer.broadcastTradeData();
        }
        catch (IOException | EncodeException e) {
          LOGGER.error(e.getMessage(), e);
        }
      }
    };

    new Thread(webSocketServer).start();
    return server;
  }

  private void startTradeDataReader() {
    Runnable worker1 = new Runnable() {
      @Override
      public void run() {
        TradeDataReader tradeDataReader = new TradeDataReader(TRADE_QUEUE);
        tradeDataReader.readFileAndPrintLines();
      }
    };
    Thread threadWorker1 = new Thread(worker1);
    threadWorker1.start();
  }

  private void startFiniteStateMachine() {
    Runnable worker2 = new Runnable() {
      @Override
      public void run() {
        FiniteStateMachine finiteStateMachine = new FiniteStateMachine(TRADE_QUEUE, OHLC_QUEUE, new OHLCEngine());
        finiteStateMachine.computeOHLC();
      }
    };

    Thread threadWorker2 = new Thread(worker2);
    threadWorker2.start();
  }
}
