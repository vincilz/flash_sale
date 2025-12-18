package com.vinci.flashsale.order.biz.dao.dataobj;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author vinci
 * @date 2025/10/28
 * @version 1.0
 * 
 */
@Data
@TableName("order_tbl")
public class OrderDO {

    @TableId
    private Long id;
    private Long userId;
    private Long productId;
    private Integer quantity;
    private Long totalPrice;

}
