package com.vinci.flashsale.order.biz.service;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
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
    @Autowired
    private IdentifierGenerator identifierGenerator;

    @Override
    public void create(String userId, String commodityCode, Integer count, Integer money) {
        Long orderId = identifierGenerator.nextId(null).longValue();
        OrderDO orderDO = new OrderDO();
        orderDO.setId(orderId);
        orderDO.setUserId(userId);
        orderDO.setCommodityCode(commodityCode);
        orderDO.setCount(count);
        orderDO.setMoney(money);
        orderMapper.insert(orderDO);
    }

}
