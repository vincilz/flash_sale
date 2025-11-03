package com.vinci.flashsale.account.biz.api;

import com.vinci.flashsale.account.api.AccountApiService;
import com.vinci.flashsale.account.biz.service.AccountService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author vinci
 * @date 2025/10/28
 * @version 1.0
 * 
 */
@DubboService(interfaceClass = AccountApiService.class, version = "1.0.0")
public class DefaultAccountApiService implements AccountApiService {

    @Autowired
    private AccountService accountService;

    @Override
    public void decrease(String userId, int money) {
        accountService.decrease(userId, money);
    }

}
