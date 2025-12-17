package com.vinci.flashsale.gateway.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.vinci.flashsale.common.exception.GlobalExceptionConstant;
import com.vinci.flashsale.gateway.routes.order.vo.OrderPurchaseReqVO;
import com.vinci.flashsale.gateway.routes.vo.CommonResult;
import lombok.Data;
import reactor.core.publisher.Mono;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/12/17
 */
@Data
public class OrderBlockHandler {

    public static Mono<CommonResult<Boolean>> purchaseBlock(
            OrderPurchaseReqVO reqVO,
            BlockException ex) {

        return Mono.just(CommonResult.failed(
                GlobalExceptionConstant.TOO_MANY_REQUEST_EXCEPTION_CODE,
                GlobalExceptionConstant.TOO_MANY_REQUEST_EXCEPTION_MSG
        ));
    }

    public static Mono<CommonResult<Boolean>> purchaseFallback(
            OrderPurchaseReqVO reqVO,
            Throwable ex) {

        return Mono.just(CommonResult.failed(
                GlobalExceptionConstant.UNAVAILABLE_EXCEPTION_CODE,
                GlobalExceptionConstant.UNAVAILABLE_EXCEPTION_MSG
        ));
    }

}
