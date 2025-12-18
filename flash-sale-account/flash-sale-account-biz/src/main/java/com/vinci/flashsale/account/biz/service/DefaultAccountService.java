package com.vinci.flashsale.account.biz.service;

import com.vinci.flashsale.DubboHolderUtils;
import com.vinci.flashsale.constant.RedisConstant;
import com.vinci.flashsale.account.biz.constant.BizConstant;
import com.vinci.flashsale.account.biz.dao.dataobj.AccountDO;
import com.vinci.flashsale.account.biz.dao.mapper.AccountMapper;
import com.vinci.flashsale.common.exception.GlobalException;
import com.vinci.flashsale.common.exception.GlobalExceptionConstant;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
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
    @Autowired
    private RedissonClient redissonClient;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reduce(Long totalPrice) {
        totalPrice = -totalPrice;
        changeUserMoney(totalPrice);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void compensateReduce(Long totalPrice) {
        changeUserMoney(totalPrice);
    }

    private void changeUserMoney(Long totalPrice) {
        Long userId = DubboHolderUtils.getUserId();
        RLock lock = redissonClient.getLock(
                String.format(
                        RedisConstant.LOCK_KEY_PATTERN,
                        BizConstant.BizType.ACCOUNT_TYPE,
                        BizConstant.BizId.REDUCE_ID,
                        userId
                )
        );

        try {
            boolean locked = lock.tryLock();
            if (!locked) {
                throw new GlobalException(GlobalExceptionConstant.LOCK_EXCEPTION);
            }
            AccountDO accountDO = accountMapper.findByUserId(userId);
            accountDO.setBalance(accountDO.getBalance() + totalPrice);
            accountMapper.updateById(accountDO);
        } finally {
            lock.unlock();
        }
    }

}
