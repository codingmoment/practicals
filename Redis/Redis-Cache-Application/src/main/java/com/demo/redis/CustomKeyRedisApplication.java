package com.demo.redis;

import java.util.UUID;

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
public class CustomKeyRedisApplication implements CommandLineRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(CustomKeyRedisApplication.class);

  @Autowired
  private CacheService cacheService;

  @Autowired
  private ControlledCacheService controlledCacheService;

  public static void main(String[] args) {
    SpringApplication.run(RedisApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    runCustomKey();
  }

  public void runCustomKey() throws Exception {
    String firstString = cacheService.cacheThis("param1", UUID.randomUUID().toString());
    LOGGER.info("First: {}", firstString);
    String secondString = cacheService.cacheThis("param1", UUID.randomUUID().toString());
    LOGGER.info("Second: {}", secondString);
    String thirdString = cacheService.cacheThis("AnotherParam", UUID.randomUUID().toString());
    LOGGER.info("Third: {}", thirdString);
    String fourthString = cacheService.cacheThis("AnotherParam", UUID.randomUUID().toString());
    LOGGER.info("Fourth: {}", fourthString);

    LOGGER.info("Starting controlled cache: -----------");
    String controlledFirst = getFromControlledCache("first");
    LOGGER.info("Controlled First: {}", controlledFirst);
    String controlledSecond = getFromControlledCache("second");
    LOGGER.info("Controlled Second: {}", controlledSecond);

    getFromControlledCache("first");
    getFromControlledCache("second");
    getFromControlledCache("third");
    // LOGGER.info("Clearing all cache entries:");
    // cacheService.forgetAboutThis("param1");
    // controlledCacheService.removeFromCache("controlledParam1");
  }

  private String getFromControlledCache(String param) {
    String fromCache = controlledCacheService.getFromCache(param);
    if (fromCache == null) {
      LOGGER.info("Oups - Cache was empty. Going to populate it");
      String newValue = controlledCacheService.populateCache(param, UUID.randomUUID().toString());
      LOGGER.info("Populated Cache with: {}", newValue);
      return newValue;
    }
    LOGGER.info("Returning from Cache: {}", fromCache);
    return fromCache;
  }
}
