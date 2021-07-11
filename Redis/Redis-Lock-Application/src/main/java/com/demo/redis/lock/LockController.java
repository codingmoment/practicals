package com.demo.redis.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locks")
public class LockController {

  @Autowired
  private RedisLockUtil redisLockUtil;

  @PostMapping("/{key}")
  public boolean lock(@PathVariable("key") String key) {
    return redisLockUtil.lock(key);
  }
}
