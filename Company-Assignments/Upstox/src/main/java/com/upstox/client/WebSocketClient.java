package com.upstox.client;

import static com.upstox.util.Constants.HOST;
import static com.upstox.util.Constants.PORT;
import static com.upstox.util.TradeGsonBuilder.OHLC_GSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import javax.websocket.ClientEndpoint;
import javax.websocket.DeploymentException;
import javax.websocket.OnMessage;
import javax.websocket.Session;

import org.glassfish.tyrus.client.ClientManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.upstox.domain.OHLC;
import com.upstox.util.OHLCDecoder;

/**
 * WebSocket client that subscribes for trade events. Use below command to run the client:
 * 
 * gradle runClient --args='stock_symbol'
 * 
 * @author N.Wani
 */
@ClientEndpoint(decoders = OHLCDecoder.class)
public class WebSocketClient {

  private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketClient.class);

  @OnMessage
  public void onMessage(OHLC ohlc, Session session) {
    LOGGER.info("Received OHLC - {}", OHLC_GSON.toJson(ohlc));
  }

  public static void main(String[] args) {

    if (args.length == 0) {
      LOGGER.error("Invalid arguments. Pass trade symbol. For example: gradle runClient --args='XYZ'");
      System.exit(0);
    }

    ClientManager client = ClientManager.createClient();

    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
      client.connectToServer(WebSocketClient.class, new URI("ws://" + HOST + ":" + PORT + "/" + args[0]));
      reader.readLine();
    }
    catch (DeploymentException | URISyntaxException | IOException e) {
      throw new RuntimeException(e);
    }
  }
}