package com.upstox.domain;

/**
 * Enum for specifying whether the OHLC packets should be sent per trade or when the bar closes.
 * 
 * @author N.Wani
 */
public enum OHLCMode {
  BY_TRADE,
  BY_BAR
}
