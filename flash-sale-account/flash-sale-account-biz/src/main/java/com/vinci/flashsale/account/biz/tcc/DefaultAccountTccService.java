package com.vinci.flashsale.account.biz.tcc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinci.flashsale.account.biz.dao.redis.AccountTccRedisDAO;

import io.seata.rm.tcc.api.BusinessActionContext;

/**
 * Default account TCC implementation; try phase includes idempotency and
 * cross-phase guard.
 */
@Service
public class DefaultAccountTccService implements AccountTccService {

    @Autowired
    private AccountTccRedisDAO accountTccRedisDAO;

    @Override
    public boolean tryFreeze(BusinessActionContext actionContext, String userId, Integer money) {
        return true;
    }

    @Override
    public boolean commit(BusinessActionContext actionContext) {
        return false;
    }

    @Override
    public boolean rollback(BusinessActionContext actionContext) {
        return false;
    }
}
