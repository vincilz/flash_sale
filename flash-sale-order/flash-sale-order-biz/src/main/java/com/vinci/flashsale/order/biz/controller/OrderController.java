package com.vinci.flashsale.order.biz.controller;

import com.vinci.flashsale.order.biz.entity.OrderPurchaseReqVO;
import com.vinci.flashsale.order.biz.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public String orderPurchase(@RequestBody OrderPurchaseReqVO reqVO) {
        orderService.orderPurchase(reqVO);
        return "success";
    }


}
