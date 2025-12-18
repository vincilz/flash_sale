package com.vinci.flashsale.storage.biz.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vinci.flashsale.storage.biz.dao.dataobj.StorageDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/10/28
 */
@Mapper
public interface StorageMapper extends BaseMapper<StorageDO> {

    default StorageDO findByCommodityCode(Long productId) {
        LambdaQueryWrapper<StorageDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StorageDO::getId, productId);
        return selectOne(queryWrapper);
    }

}
