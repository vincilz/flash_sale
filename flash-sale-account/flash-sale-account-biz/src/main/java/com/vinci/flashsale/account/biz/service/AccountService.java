package com.vinci.flashsale.account.biz.service;

import org.springframework.stereotype.Service;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/10/28
 */
@Service
public interface AccountService {

    void decrease(String userId, Integer money);

}
