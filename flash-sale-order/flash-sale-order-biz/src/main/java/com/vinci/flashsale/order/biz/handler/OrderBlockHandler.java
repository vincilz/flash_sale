package com.vinci.flashsale.order.biz.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.vinci.flashsale.common.dto.CommonResponse;
import com.vinci.flashsale.common.exception.GlobalExceptionConstant;
import com.vinci.flashsale.common.utils.CommonResponseUtils;
import reactor.core.publisher.Mono;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/12/17
 */
public class OrderBlockHandler {

    public static CommonResponse purchaseBlock(
            String userId,
            String commodityCode,
            Integer count,
            Integer money,
            BlockException ex) {

        return CommonResponseUtils.failed(
            GlobalExceptionConstant.UNAVAILABLE_EXCEPTION_CODE,
            GlobalExceptionConstant.UNAVAILABLE_EXCEPTION_MSG
        );
    }

    public static CommonResponse purchaseFallback(
            String userId,
            String commodityCode,
            Integer count,
            Integer money,
            Throwable ex) {

        return CommonResponseUtils.failed(
            GlobalExceptionConstant.UNAVAILABLE_EXCEPTION_CODE,
            GlobalExceptionConstant.UNAVAILABLE_EXCEPTION_MSG
        );
    }

}
