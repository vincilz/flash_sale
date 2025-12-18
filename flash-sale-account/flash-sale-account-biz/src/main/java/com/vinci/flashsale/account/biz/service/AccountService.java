package com.vinci.flashsale.account.biz.service;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/10/28
 */
public interface AccountService {

    void reduce(Long totalPrice);

    void compensateReduce(Long totalPrice);

}
