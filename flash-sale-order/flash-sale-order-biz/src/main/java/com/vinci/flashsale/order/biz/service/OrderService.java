package com.vinci.flashsale.order.biz.service;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/12/17
 */
public interface OrderService {

    void create(String userId, String commodityCode, Integer count, Integer money);

}
