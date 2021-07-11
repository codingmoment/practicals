package com.demo.redis.lock;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisLockUtil {

  private static final String LOCK_PREFIX = "DEMO_LOCK_";
  private static final int LOCK_EXPIRE_TIME_MS = 30 * 1000;
  private static final String LOCK_VALUE = "LOCKED";

  private final RedisTemplate<String, String> redisTemplate;

  public RedisLockUtil(RedisTemplate<String, String> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public boolean lock(String key) {
    key = LOCK_PREFIX + key;
    if (redisTemplate.hasKey(key)) {
      return false;
    }
    ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
    valueOps.set(key, LOCK_VALUE, LOCK_EXPIRE_TIME_MS, TimeUnit.MILLISECONDS);
    return true;
  }
}
