package com.demo.redis.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cache")
public class CacheConfigurationProperties {

  // If you want to fetch data only once a minute,
  // just guard it with a @Cacheable Annotation and set the TTL to 1 minute.
  private long timeoutSeconds = 60;
  private int redisPort = 6379;
  private String redisHost = "localhost";
  // Mapping of cacheNames to expira-after-write timeout in seconds
  private Map<String, Long> cacheExpirations = new HashMap<>();

  public long getTimeoutSeconds() {
    return timeoutSeconds;
  }

  public void setTimeoutSeconds(long timeoutSeconds) {
    this.timeoutSeconds = timeoutSeconds;
  }

  public int getRedisPort() {
    return redisPort;
  }

  public void setRedisPort(int redisPort) {
    this.redisPort = redisPort;
  }

  public String getRedisHost() {
    return redisHost;
  }

  public void setRedisHost(String redisHost) {
    this.redisHost = redisHost;
  }

  public Map<String, Long> getCacheExpirations() {
    return cacheExpirations;
  }

  public void setCacheExpirations(Map<String, Long> cacheExpirations) {
    this.cacheExpirations = cacheExpirations;
  }

}
