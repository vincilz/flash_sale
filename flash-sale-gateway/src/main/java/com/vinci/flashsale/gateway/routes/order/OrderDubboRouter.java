package com.vinci.flashsale.gateway.routes.order;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.vinci.flashsale.gateway.handler.OrderBlockHandler;
import com.vinci.flashsale.gateway.routes.order.vo.OrderPurchaseReqVO;
import com.vinci.flashsale.gateway.routes.vo.CommonResult;
import com.vinci.flashsale.order.api.OrderApiService;
import com.vinci.flashsale.order.dto.OrderPurchaseRequest;
import jakarta.validation.Valid;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/12/16
 */
@RestController
@RequestMapping("/api/order")
public class OrderDubboRouter {

    @DubboReference(group = "DUBBO_GROUP", version = "1.0.0")
    private OrderApiService orderApiService;

    @PostMapping("/purchase")
    @SentinelResource(
            value = "order_purchase",
            blockHandler = "handlerBlock",
            blockHandlerClass = OrderBlockHandler.class,
            fallback = "handlerFallback"
            , fallbackClass = OrderBlockHandler.class
    )
    public Mono<CommonResult<Boolean>> purchase(@Valid @RequestBody OrderPurchaseReqVO reqVO) {
        return Mono.fromCallable(() -> {
            OrderPurchaseRequest dubboRequest = OrderPurchaseRequest.newBuilder()
                    .setUserId(reqVO.getUserId())
                    .setCommodityCode(reqVO.getCommodityCode())
                    .setCount(reqVO.getCount())
                    .setMoney(reqVO.getMoney())
                    .build();
            orderApiService.purchase(dubboRequest);
            return true;
        })
        .subscribeOn(Schedulers.boundedElastic())
        .map(CommonResult::success);
    }

}
