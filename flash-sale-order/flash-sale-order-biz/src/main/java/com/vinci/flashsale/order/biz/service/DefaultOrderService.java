package com.vinci.flashsale.order.biz.service;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.vinci.flashsale.DubboHolderUtils;
import com.vinci.flashsale.order.biz.dao.dataobj.OrderDO;
import com.vinci.flashsale.order.biz.dao.mapper.OrderMapper;
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
    public void create(Long productId, Integer quantity, Long totalPrice) {
        Long userId = DubboHolderUtils.getUserId();
        Long orderId = identifierGenerator.nextId(null).longValue();
        OrderDO orderDO = new OrderDO();
        orderDO.setId(orderId);
        orderDO.setUserId(userId);
        orderDO.setProductId(productId);
        orderDO.setQuantity(quantity);
        orderDO.setTotalPrice(totalPrice);
        orderMapper.insert(orderDO);
    }

}
