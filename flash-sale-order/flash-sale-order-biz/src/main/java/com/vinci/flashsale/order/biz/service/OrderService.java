package com.vinci.flashsale.order.biz.service;

import com.vinci.flashsale.order.biz.entity.OrderPurchaseReqVO;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/10/28
 */
public interface OrderService {

    void orderPurchase(OrderPurchaseReqVO reqVO);

    void create(String userId, String commodityCode, Integer count, Integer money);

}
