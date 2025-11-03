package com.vinci.flashsale.order.biz.entity;

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
    private Integer id;
    private String userId;
    private String commodityCode;
    private Integer count;
    private Integer money;

}
