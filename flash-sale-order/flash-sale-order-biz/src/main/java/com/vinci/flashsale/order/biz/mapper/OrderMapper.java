package com.vinci.flashsale.order.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vinci.flashsale.order.biz.entity.OrderDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/10/28
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderDO> {
}
