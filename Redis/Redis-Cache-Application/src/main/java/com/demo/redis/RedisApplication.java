package com.demo.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.demo.redis.service.CacheService;
import com.demo.redis.service.ControlledCacheService;

@EnableCaching
@SpringBootApplication
public class RedisApplication implements CommandLineRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(RedisApplication.class);

  @Autowired
  private CacheService cacheService;

  @Autowired
  private ControlledCacheService controlledCacheService;

  public static void main(String[] args) {
    SpringApplication.run(RedisApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    // getFromControlledCache();
  }

  private void runCacheThis() {
    String firstString = cacheService.cacheThis();
    LOGGER.info("First: {}", firstString);
    String secondString = cacheService.cacheThis();
    LOGGER.info("Second: {}", secondString);
  }

  private void runGetFromControlledCache() {
    LOGGER.info("Starting controlled cache: -----------");
    String controlledFirst = getFromControlledCache();
    LOGGER.info("Controlled First: {}", controlledFirst);
    String controlledSecond = getFromControlledCache();
    LOGGER.info("Controlled Second: {}", controlledSecond);
  }

  private String getFromControlledCache() {
    String fromCache = controlledCacheService.getFromCache();
    if (fromCache == null) {
      LOGGER.info("Oups - Cache was empty. Going to populate it");
      String newValue = controlledCacheService.populateCache();
      LOGGER.info("Populated Cache with: {}", newValue);
      return newValue;
    }
    LOGGER.info("Returning from Cache: {}", fromCache);
    return fromCache;
  }

  private void clearAllCache() {
    LOGGER.info("Clearing all cache entries:");
    cacheService.forgetAboutThis();
    controlledCacheService.removeFromCache();
  }
}
