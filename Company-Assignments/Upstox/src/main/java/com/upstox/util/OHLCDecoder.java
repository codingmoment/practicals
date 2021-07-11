package com.upstox.util;

import static com.upstox.util.TradeGsonBuilder.OHLC_GSON;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.upstox.domain.OHLC;

public class OHLCDecoder implements Decoder.Text<OHLC> {

  @Override
  public void init(EndpointConfig config) {
  }

  @Override
  public void destroy() {
  }

  @Override
  public OHLC decode(String s) throws DecodeException {
    return OHLC_GSON.fromJson(s, OHLC.class);
  }

  @Override
  public boolean willDecode(String s) {
    return (s != null);
  }

}
