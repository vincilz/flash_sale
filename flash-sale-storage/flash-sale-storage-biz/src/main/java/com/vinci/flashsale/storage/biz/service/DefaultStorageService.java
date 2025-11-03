package com.vinci.flashsale.storage.biz.service;

import com.vinci.flashsale.storage.biz.entity.StorageDO;
import com.vinci.flashsale.storage.biz.mapper.StorageMapper;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/10/28
 */
@Service
public class DefaultStorageService implements StorageService {

    @Autowired
    private StorageMapper storageMapper;

    @Override
    @GlobalTransactional(name = "purchase_order", rollbackFor = Exception.class)
    public void decrease(String commodityCode, Integer count) {
        synchronized (DefaultStorageService.class) {
            StorageDO storageDO = storageMapper.findByCommodityCode(commodityCode);
            storageDO.setCount(storageDO.getCount() - count);
            storageMapper.updateById(storageDO);
        }
    }

}
