package com.upstox.registry;

import javax.websocket.Session;

import com.upstox.server.WebSocketServer;

/**
 * Client subscription
 * 
 * @author N.Wani
 *
 */
public class Subscription {
  private Session webSocketSession;
  private String symbol;
  private String sessionId;
  private WebSocketServer endpoint;

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  public WebSocketServer getEndpoint() {
    return endpoint;
  }

  public void setEndpoint(WebSocketServer endpoint) {
    this.endpoint = endpoint;
  }

  public Session getWebSocketSession() {
    return webSocketSession;
  }

  public void setWebSocketSession(Session webSocketSession) {
    this.webSocketSession = webSocketSession;
  }

}
