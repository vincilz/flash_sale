package com.vinci.flashsale.order.biz.entity;

import lombok.Data;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/10/28
 */
@Data
public class OrderPurchaseReqVO {

    private String userId;
    private String commodityCode;
    private Integer count;
    private Integer money;

}
