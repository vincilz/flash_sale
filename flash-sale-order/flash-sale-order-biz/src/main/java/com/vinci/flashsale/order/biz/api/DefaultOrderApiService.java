package com.vinci.flashsale.order.biz.api;

import com.vinci.flashsale.order.api.OrderApiService;
import com.vinci.flashsale.order.biz.service.OrderService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/10/28
 */
@DubboService(interfaceClass = OrderApiService.class, version = "1.0.0")
public class DefaultOrderApiService implements OrderApiService {

    @Autowired
    private OrderService orderService;

    @Override
    public void create(String userId, String commodityCode, Integer count, Integer money) {
        orderService.create(userId, commodityCode, count, money);
    }

}
