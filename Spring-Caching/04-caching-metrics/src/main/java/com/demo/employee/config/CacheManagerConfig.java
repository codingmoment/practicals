package com.demo.employee.config;

import java.io.Serializable;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.expiry.ExpiryPolicy;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "demo.cache")
public class CacheManagerConfig implements JCacheManagerCustomizer {

  private Map<String, CacheConfig> cacheConfigurations = new HashMap<>();

  public Map<String, CacheConfig> getCacheConfigurations() {
    return cacheConfigurations;
  }

  public void setCacheConfigurations(Map<String, CacheConfig> cacheConfigurations) {
    this.cacheConfigurations = cacheConfigurations;
  }

  @Override
  public void customize(javax.cache.CacheManager cacheManager) {
    cacheConfigurations.forEach((cacheName, cacheConfig) -> {
      // Setting maxEntriesLocalHeap
      ResourcePoolsBuilder poolBuilder = ResourcePoolsBuilder.heap(cacheConfig.getMaxEntriesLocalHeap());
      // Setting maxEntriesLocalDisk
      if (cacheConfig.getMaxEntriesLocalDisk() > 0) {
        poolBuilder.disk(cacheConfig.getMaxEntriesLocalDisk(), MemoryUnit.MB, true);
      }

      // Setting timeToLiveSeconds
      ExpiryPolicy<Object, Object> timeToLiveExpiration = ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(cacheConfig.getTimeToLiveSeconds()));

      // Creating cache for cacheName
      // We use Object.class for both key and value class types
      cacheManager.createCache(cacheName, Eh107Configuration.fromEhcacheCacheConfiguration(CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class, poolBuilder).withExpiry(timeToLiveExpiration)));
    });
  }

  static class CacheConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private Integer maxEntriesLocalHeap = 100;
    private Integer maxEntriesLocalDisk = 0;
    private Integer timeToLiveSeconds = 3600;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public Integer getMaxEntriesLocalHeap() {
      return maxEntriesLocalHeap;
    }

    public void setMaxEntriesLocalHeap(Integer maxEntriesLocalHeap) {
      this.maxEntriesLocalHeap = maxEntriesLocalHeap;
    }

    public Integer getMaxEntriesLocalDisk() {
      return maxEntriesLocalDisk;
    }

    public void setMaxEntriesLocalDisk(Integer maxEntriesLocalDisk) {
      this.maxEntriesLocalDisk = maxEntriesLocalDisk;
    }

    public Integer getTimeToLiveSeconds() {
      return timeToLiveSeconds;
    }

    public void setTimeToLiveSeconds(Integer timeToLiveSeconds) {
      this.timeToLiveSeconds = timeToLiveSeconds;
    }

  }

}
