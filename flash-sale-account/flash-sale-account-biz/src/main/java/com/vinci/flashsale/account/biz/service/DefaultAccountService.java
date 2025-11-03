package com.vinci.flashsale.account.biz.service;

import com.vinci.flashsale.account.biz.entity.AccountDO;
import com.vinci.flashsale.account.biz.mapper.AccountMapper;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/10/28
 */
@Service("accountService")
public class DefaultAccountService implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    @GlobalTransactional(name = "purchase_order", rollbackFor = Exception.class)
    public void decrease(String userId, Integer money) {
        synchronized (DefaultAccountService.class) {
            AccountDO accountDO = accountMapper.findByUserId(userId);
            accountDO.setMoney(accountDO.getMoney() - money);
            accountMapper.updateById(accountDO);
        }
    }

}
