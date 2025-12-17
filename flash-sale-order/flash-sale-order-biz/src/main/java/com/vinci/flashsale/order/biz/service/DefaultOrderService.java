package com.vinci.flashsale.order.biz.service;

import com.vinci.flashsale.order.biz.entity.OrderDO;
import com.vinci.flashsale.order.biz.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/12/17
 */
@Service
public class DefaultOrderService implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void create(String userId, String commodityCode, Integer count, Integer money) {
        OrderDO orderDO = new OrderDO();
        orderDO.setUserId(userId);
        orderDO.setCommodityCode(commodityCode);
        orderDO.setCount(count);
        orderDO.setMoney(money);
        orderMapper.insert(orderDO);
    }

}
