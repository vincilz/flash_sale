package com.vinci.flashsale.order.biz.facade;

import com.vinci.flashsale.common.dto.CommonResponse;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/10/28
 */
public interface OrderFacade {

    CommonResponse orderPurchase(String userId, String commodityCode, Integer count, Integer money);

}
