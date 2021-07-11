package com.upstox.util;

import static com.upstox.util.TradeGsonBuilder.OHLC_GSON;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.upstox.domain.OHLC;

public class OHLCEncoder implements Encoder.Text<OHLC> {

  @Override
  public void init(EndpointConfig config) {
  }

  @Override
  public void destroy() {
  }

  @Override
  public String encode(OHLC object) throws EncodeException {
    return OHLC_GSON.toJson(object);
  }

}
