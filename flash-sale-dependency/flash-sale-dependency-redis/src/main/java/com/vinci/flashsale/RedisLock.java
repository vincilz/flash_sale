package com.vinci.flashsale;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/12/16
 */
@Repository
public class RedisLock {

    private static final String KEY_PATTERN = "lock:%s:%s:%s";

    @Autowired
    private RedissonClient redissonClient;

    public RLock getLock(String bizType, String bizId, String userId) {
        return redissonClient.getLock(String.format(KEY_PATTERN, bizType, bizId, userId));
    }

    public boolean tryLock(RLock lock) throws InterruptedException {
        return lock.tryLock(10, TimeUnit.MINUTES);
    }

    public void unlock(RLock lock) {
        lock.unlock();
    }

}
