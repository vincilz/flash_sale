package com.vinci.flashsale.order.api;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/10/28
 */
public interface OrderApiService {
    void create(String userId, String commodityCode, Integer count, Integer money);
}
