package com.vinci.flashsale.order.biz.facade;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.vinci.flashsale.account.api.AccountApiService;
import com.vinci.flashsale.account.dto.AccountReduceRequest;
import com.vinci.flashsale.common.dto.CommonResponse;
import com.vinci.flashsale.common.utils.CommonResponseUtils;
import com.vinci.flashsale.order.biz.handler.OrderBlockHandler;
import com.vinci.flashsale.order.biz.dao.mapper.OrderMapper;
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
    public CommonResponse orderPurchase(Long productId, Integer quantity, Long totalPrice) {
        // 创建订单
        orderService.create(productId, quantity, totalPrice);

        // 扣减库存
        StorageReduceRequest storageDecreaseRequest = StorageReduceRequest.newBuilder()
                .setProductId(productId)
                .setQuantity(quantity)
                .build();
        storageApiService.reduce(storageDecreaseRequest);

        // 扣减账户
        AccountReduceRequest accountDecreaseRequest = AccountReduceRequest.newBuilder()
                .setTotalPrice(totalPrice)
                .build();
        accountApiService.reduce(accountDecreaseRequest);

        return CommonResponseUtils.success();
    }

}
