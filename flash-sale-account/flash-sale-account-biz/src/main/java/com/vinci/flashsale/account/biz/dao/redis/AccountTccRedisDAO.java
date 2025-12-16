package com.vinci.flashsale.account.biz.dao.redis;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.Duration;

/**
 * Redisson-based TCC freeze record DAO; guards idempotency and cross-phase conflicts.
 */
@Repository
public class AccountTccRedisDAO {

    private static final String KEY_PATTERN = "tcc:%s:%s:%s"; // tcc:bizId:tid:xid
    private static final Duration TTL = Duration.ofMinutes(10);

    @Autowired
    private RedissonClient redissonClient;

    public enum ChangeStateResult {
        UPDATED,
        IDEMPOTENT_TARGET,
        CONFLICT,
        NOT_EXIST
    }
}
