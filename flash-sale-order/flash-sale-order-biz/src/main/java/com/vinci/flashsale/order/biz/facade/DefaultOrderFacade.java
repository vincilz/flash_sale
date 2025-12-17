package com.vinci.flashsale.order.biz.facade;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.vinci.flashsale.account.api.AccountApiService;
import com.vinci.flashsale.account.dto.AccountReduceRequest;
import com.vinci.flashsale.common.dto.CommonResponse;
import com.vinci.flashsale.common.utils.CommonResponseUtils;
import com.vinci.flashsale.order.biz.handler.OrderBlockHandler;
import com.vinci.flashsale.order.biz.mapper.OrderMapper;
import com.vinci.flashsale.order.biz.service.OrderService;
import com.vinci.flashsale.storage.api.StorageApiService;
import com.vinci.flashsale.storage.dto.StorageReduceRequest;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/10/28
 */
@Service
public class DefaultOrderFacade implements OrderFacade {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderService orderService;

    @DubboReference(group = "DUBBO_GROUP", version = "1.0.0")
    private AccountApiService accountApiService;
    @DubboReference(group = "DUBBO_GROUP", version = "1.0.0")
    private StorageApiService storageApiService;

    @Override
    @SentinelResource(
            value = "order_purchase",
            blockHandlerClass = OrderBlockHandler.class,
            blockHandler = "purchaseBlock",
            fallbackClass = OrderBlockHandler.class,
            fallback = "purchaseFallback"
    )
    public CommonResponse orderPurchase(String userId, String commodityCode, Integer count, Integer money) {
        // 创建订单
        orderService.create(userId, commodityCode, count, money);

        // 扣减库存
        StorageReduceRequest storageDecreaseRequest = StorageReduceRequest.newBuilder()
                .setCommodityCode(commodityCode)
                .setCount(count)
                .build();
        storageApiService.reduce(storageDecreaseRequest);

        // 扣减账户
        AccountReduceRequest accountDecreaseRequest = AccountReduceRequest.newBuilder()
                .setUserId(userId)
                .setMoney(money)
                .build();
        accountApiService.reduce(accountDecreaseRequest);

        return CommonResponseUtils.success();
    }

}
