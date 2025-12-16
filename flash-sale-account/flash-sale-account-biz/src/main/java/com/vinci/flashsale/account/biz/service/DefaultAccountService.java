package com.vinci.flashsale.account.biz.service;

import com.vinci.flashsale.RedisLock;
import com.vinci.flashsale.account.biz.constant.BizConstant;
import com.vinci.flashsale.account.biz.dao.dataobj.AccountDO;
import com.vinci.flashsale.account.biz.dao.mapper.AccountMapper;
import com.vinci.flashsale.common.exception.GlobalException;
import com.vinci.flashsale.common.exception.GlobalExceptionConstant;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/10/28
 */
@Service("accountService")
public class DefaultAccountService implements AccountService {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired(required = false)
    private RedisLock redisLock;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reduce(String userId, Integer money) {
        money = -money;
        changeUserMoney(userId, money);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void compensateReduce(String userId, Integer money) {
        changeUserMoney(userId, money);
    }

    private void changeUserMoney(String userId, Integer money) {
        RLock lock = redisLock.getLock(BizConstant.BizType.ACCOUNT_TYPE, BizConstant.BizId.REDUCE_ID, userId);
        try {
            boolean locked = redisLock.tryLock(lock);
            if (!locked) {
                throw new GlobalException(GlobalExceptionConstant.LOCK_EXCEPTION);
            }
            AccountDO accountDO = accountMapper.findByUserId(userId);
            accountDO.setMoney(accountDO.getMoney() + money);
            accountMapper.updateById(accountDO);
        } catch (InterruptedException exception) {
            throw new GlobalException(exception);
        } finally {
            redisLock.unlock(lock);
        }
    }

}
