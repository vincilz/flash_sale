package com.vinci.flashsale.order.biz.service;

import com.vinci.flashsale.account.api.AccountApiService;
import com.vinci.flashsale.account.dto.AccountReduceRequest;
import com.vinci.flashsale.order.biz.entity.OrderDO;
import com.vinci.flashsale.order.biz.entity.OrderPurchaseReqVO;
import com.vinci.flashsale.order.biz.mapper.OrderMapper;
import com.vinci.flashsale.storage.api.StorageApiService;
import com.vinci.flashsale.storage.dto.StorageReduceRequest;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/10/28
 */
@Service
public class DefaultOrderService implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @DubboReference(group = "DUBBO_GROUP", version = "1.0.0")
    private AccountApiService accountApiService;
    @DubboReference(group = "DUBBO_GROUP", version = "1.0.0")
    private StorageApiService storageApiService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void orderPurchase(OrderPurchaseReqVO reqVO) {
        // 创建订单
        create(reqVO.getUserId(), reqVO.getCommodityCode(), reqVO.getCount(), reqVO.getMoney());
        // 扣减库存
        StorageReduceRequest storageDecreaseRequest = StorageReduceRequest.newBuilder()
                .setCommodityCode(reqVO.getCommodityCode())
                .setCount(reqVO.getCount())
                .build();
        storageApiService.reduce(storageDecreaseRequest);
        // 扣减账户
        AccountReduceRequest accountDecreaseRequest = AccountReduceRequest.newBuilder()
                .setUserId(reqVO.getUserId())
                .setMoney(reqVO.getMoney())
                .build();
        accountApiService.reduce(accountDecreaseRequest);
    }

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
