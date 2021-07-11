package com.demo.redis.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ControlledCacheService {

  private static final String CONTROLLED_PREFIX = "myControlledPrefix_";

  public static String getCacheKey(String relevant) {
    return CONTROLLED_PREFIX + relevant;
  }

  // The method gets invoked only when there is nothing in the cache.
  // The returned null value is only a marker for a Cache Miss.
  // By default, Spring Data Redis is not caching null values.
  @Cacheable(cacheNames = "myControlledCache")
  public String getFromCache() {
    return null;
  }

  // CachePut puts the returned value into the cache.
  // But it does not check if there is an existing key in the cache.
  @CachePut(cacheNames = "myControlledCache")
  public String populateCache() {
    return "this is it again!";
  }

  // Evicts cache entries
  @CacheEvict(cacheNames = "myControlledCache")
  public void removeFromCache() {
  }

  // Here, we have defined the format of the key in Cacheable annotation.
  // We are using static method to generate the key.
  @Cacheable(cacheNames = "myControlledCache", key = "T(com.demo.redis.service.ControlledCacheService).getCacheKey(#relevant)")
  public String getFromCache(String relevant) {
    return null;
  }

  @CacheEvict(cacheNames = "myControlledCache", key = "T(com.demo.redis.service.ControlledCacheService).getCacheKey(#relevant)")
  public void removeFromCache(String relevant) {
  }

  @CachePut(cacheNames = "myControlledCache", key = "T(com.demo.redis.service.ControlledCacheService).getCacheKey(#relevant)")
  public String populateCache(String relevant, String unrelevantTrackingId) {
    return "this is it again!";
  }
}
