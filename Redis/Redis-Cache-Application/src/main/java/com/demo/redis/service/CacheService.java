package com.demo.redis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

  private static final Logger LOGGER = LoggerFactory.getLogger(CacheService.class);

  @Cacheable(cacheNames = "myCache")
  public String cacheThis() {
    LOGGER.info("Returning NOT from cache!");
    return "this is it";
  }

  @CacheEvict(cacheNames = "myCache")
  public void forgetAboutThis() {
    LOGGER.info("Forgetting everything about this!");
  }

  // Here, we have defined the format of the key in Cacheable annotation.
  // The value of key format is written in SpEL (Spring Expression Language).
  @Cacheable(cacheNames = "myCache", key = "'myPrefix_'.concat(#relevant)")
  public String cacheThis(String relevant, String unrelevantTrackingId) {
    LOGGER.info("Returning NOT from cache!. Tracking: {}!", unrelevantTrackingId);
    return "this is it";
  }

  @CacheEvict(cacheNames = "myCache", key = "'myPrefix_'.concat(#relevant)")
  public void forgetAboutThis(String relevant) {
    LOGGER.info("Forgetting everything about this '{}'!", relevant);
  }
}
