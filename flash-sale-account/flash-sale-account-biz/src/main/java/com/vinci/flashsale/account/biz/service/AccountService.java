package com.vinci.flashsale.account.biz.service;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/10/28
 */
public interface AccountService {

    void reduce(String userId, Integer money);

    void compensateReduce(String userId, Integer money);

}
