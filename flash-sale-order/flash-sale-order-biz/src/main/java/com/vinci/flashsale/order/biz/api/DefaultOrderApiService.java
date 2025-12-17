package com.vinci.flashsale.order.biz.api;

import com.vinci.flashsale.common.dto.CommonResponse;
import com.vinci.flashsale.common.utils.CommonResponseUtils;
import com.vinci.flashsale.order.api.OrderApiService;
import com.vinci.flashsale.order.biz.service.OrderService;
import com.vinci.flashsale.order.dto.OrderPurchaseRequest;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/10/28
 */
@Service
@DubboService(version = "1.0.0")
public class DefaultOrderApiService implements OrderApiService {

    private static final Executor ORDER_EXECUTOR = Executors.newFixedThreadPool(8);

    @Autowired
    private OrderService orderService;


    @Override
    public CommonResponse purchase(OrderPurchaseRequest request) {
        orderService.orderPurchase(
                request.getUserId(),
                request.getCommodityCode(),
                request.getCount(),
                request.getMoney()
        );
        return CommonResponseUtils.success();
    }

    @Override
    public CompletableFuture<CommonResponse> purchaseAsync(OrderPurchaseRequest request) {
        return CompletableFuture.supplyAsync(() -> purchase(request), ORDER_EXECUTOR);
    }
}
