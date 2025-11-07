package com.vinci.flashsale.order.biz.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/10/28
 */
@Data
public class OrderPurchaseReqVO {

    @NotNull(message = "用户ID不能为空")
    private String userId;
    @NotNull(message = "商品ID不能为空")
    private String commodityCode;
    @NotNull(message = "商品数量不能为空")
    private Integer count;
    @NotNull(message = "商品单价不能为空")
    private Integer money;

}
