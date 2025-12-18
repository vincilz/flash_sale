package com.vinci.flashsale.gateway.routes.order.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/12/17
 */
@Data
public class OrderPurchaseReqVO {

    @NotNull(message = "商品ID不能为空")
    private Long productId;
    @NotNull(message = "商品数量不能为空")
    private Integer quantity;
    @NotNull(message = "商品单价不能为空")
    private Long totalPrice;

}
