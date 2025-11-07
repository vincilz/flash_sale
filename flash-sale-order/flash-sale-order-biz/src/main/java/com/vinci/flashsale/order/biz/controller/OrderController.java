package com.vinci.flashsale.order.biz.controller;

import com.vinci.flashsale.common.vo.CommonResult;
import com.vinci.flashsale.order.biz.entity.OrderPurchaseReqVO;
import com.vinci.flashsale.order.biz.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/10/28
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order/purchase")
    public Mono<CommonResult<Boolean>> orderPurchase(@RequestBody @Valid Mono<OrderPurchaseReqVO> monoReqVO) {
        return monoReqVO
                .flatMap(reqVO ->
                    Mono.fromCallable(() -> {
                        orderService.orderPurchase(reqVO);
                        return true;
                    }).subscribeOn(Schedulers.boundedElastic())
                )
                .map(CommonResult::success);
    }


}
