package com.upstox.util;

import static com.upstox.util.Constants.OHLC_BAR_NUMBER;
import static com.upstox.util.Constants.OHLC_CLOSE;
import static com.upstox.util.Constants.OHLC_HIGH;
import static com.upstox.util.Constants.OHLC_LOW;
import static com.upstox.util.Constants.OHLC_OPEN;
import static com.upstox.util.Constants.OHLC_SYMBOL;
import static com.upstox.util.Constants.OHLC_VOLUME;
import static com.upstox.util.Constants.PRICE;
import static com.upstox.util.Constants.QUANTITY;
import static com.upstox.util.Constants.SYMBOL;
import static com.upstox.util.Constants.TIMESTAMP;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.upstox.domain.OHLC;
import com.upstox.domain.Trade;

public class TradeGsonBuilder {

  public static final Gson TRADE_GSON = buildTradeGson();
  public static final Gson OHLC_GSON = buildOHLCGson();

  private static Gson buildTradeGson() {
    GsonBuilder gsonBuilder = new GsonBuilder();

    JsonDeserializer<Trade> deserializer = new JsonDeserializer<Trade>() {
      @Override
      public Trade deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        String symbol = jsonObject.get(SYMBOL).getAsString();
        double price = jsonObject.get(PRICE).getAsDouble();
        double quantity = jsonObject.get(QUANTITY).getAsDouble();
        long nanoseconds = jsonObject.get(TIMESTAMP).getAsLong();
        long milliseconds = TimeUnit.MILLISECONDS.convert(nanoseconds, TimeUnit.NANOSECONDS);
        LocalDateTime timestamp = LocalDateTime.ofInstant(Instant.ofEpochMilli(milliseconds), ZoneOffset.UTC);
        return new Trade(symbol, price, quantity, timestamp);
      }
    };

    gsonBuilder.registerTypeAdapter(Trade.class, deserializer);
    return gsonBuilder.create();
  }

  private static Gson buildOHLCGson() {
    GsonBuilder gsonBuilder = new GsonBuilder();

    JsonDeserializer<OHLC> deserializer = new JsonDeserializer<OHLC>() {
      @Override
      public OHLC deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        String symbol = jsonObject.get(OHLC_SYMBOL).getAsString();
        double open = jsonObject.get(OHLC_OPEN) != null ? jsonObject.get(OHLC_OPEN).getAsDouble() : -1;
        double high = jsonObject.get(OHLC_HIGH) != null ? jsonObject.get(OHLC_HIGH).getAsDouble() : -1;
        double low = jsonObject.get(OHLC_LOW) != null ? jsonObject.get(OHLC_LOW).getAsDouble() : -1;
        double close = jsonObject.get(OHLC_CLOSE) != null ? jsonObject.get(OHLC_CLOSE).getAsDouble() : -1;
        double volume = jsonObject.get(OHLC_VOLUME) != null ? jsonObject.get(OHLC_VOLUME).getAsDouble() : -1;
        long barNumber = jsonObject.get(OHLC_BAR_NUMBER).getAsLong();
        return new OHLC(symbol, open, high, low, close, volume, null, barNumber);
      }
    };

    JsonSerializer<OHLC> serializer = new JsonSerializer<OHLC>() {
      @Override
      public JsonElement serialize(OHLC ohlc, Type typeOfT, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(OHLC_SYMBOL, ohlc.getSymbol());
        if (ohlc.getOpen() >= 0) {
          jsonObject.addProperty(OHLC_OPEN, ohlc.getOpen());
          jsonObject.addProperty(OHLC_HIGH, ohlc.getHigh());
          jsonObject.addProperty(OHLC_LOW, ohlc.getLow());
          jsonObject.addProperty(OHLC_CLOSE, ohlc.getClose());
          jsonObject.addProperty(OHLC_VOLUME, ohlc.getVolume());
        }
        jsonObject.addProperty(OHLC_BAR_NUMBER, ohlc.getBarNumber());
        return jsonObject;
      }
    };

    gsonBuilder.registerTypeAdapter(OHLC.class, deserializer);
    gsonBuilder.registerTypeAdapter(OHLC.class, serializer);
    return gsonBuilder.create();
  }

}
