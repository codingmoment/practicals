package com.demo.redis.config;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @formatter:off
 * 
 * We put a @Configuration on top of the class to make it visible to the Spring Container.
 * 
 * We enable the ConfigurationProperties we just created before by referencing them via the @EnableConfigurationProperties annotation.
 * 
 * We create four Spring Beans:
 * 
 * 1. A LettuceConnectionFactory (redisConnectionFactory) which uses our properties to set the Hostname and the Port of the Redis Connection.
 * 2. A RedisTemplate which is overriding the default RedisTemplate in order to leverage the just created RedisConnectionFactory.
 * 3. RedisCacheConfiguration to provide our Redis default Configuration for places which are not managed by us at the moment.
 * 4. CacheManager: It is the hearth of our Cache Implementation. Here we define our CacheConfigurations. Here we create a Configuration for every cache we specified in the properties. The configuration gets created
 *    with the timeout specified in the Map contained in our properties.
 * 
 * SO WHAT DID WE ACHIEVE? We created a way on how we can re-configure our TTLs without touching our code. 
 * 
 * @author N.Wani
 * @formatter:on
 *
 */
@Configuration
@EnableConfigurationProperties(CacheConfigurationProperties.class)
public class CacheConfig extends CachingConfigurerSupport {

  private static final Logger LOGGER = LoggerFactory.getLogger(CacheConfig.class);

  @Bean
  public LettuceConnectionFactory redisConnectionFactory(CacheConfigurationProperties properties) {
    LOGGER.info("Redis (/Lettuce) configuration enabled. With cache timeout " + properties.getTimeoutSeconds() + " seconds.");

    RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
    redisStandaloneConfiguration.setHostName(properties.getRedisHost());
    redisStandaloneConfiguration.setPort(properties.getRedisPort());
    return new LettuceConnectionFactory(redisStandaloneConfiguration);
  }

  @Bean
  public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
    RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(cf);
    return redisTemplate;
  }

  @Bean
  public RedisCacheConfiguration cacheConfiguration(CacheConfigurationProperties properties) {
    return createCacheConfiguration(properties.getTimeoutSeconds());
  }

  @Bean
  public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory, CacheConfigurationProperties properties) {
    Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();

    for (Entry<String, Long> cacheNameAndTimeout : properties.getCacheExpirations().entrySet()) {
      cacheConfigurations.put(cacheNameAndTimeout.getKey(), createCacheConfiguration(cacheNameAndTimeout.getValue()));
    }

    // @formatter:off
    return RedisCacheManager
            .builder(redisConnectionFactory)
            .cacheDefaults(createCacheConfiguration(properties.getTimeoutSeconds()))
            .withInitialCacheConfigurations(cacheConfigurations)
            .build();
    // @formatter:on
  }

  private static RedisCacheConfiguration createCacheConfiguration(long timeoutInSeconds) {
    return RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(timeoutInSeconds));
  }
}
