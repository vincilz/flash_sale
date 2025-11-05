package com.vinci.flashsale.storage.biz.service;

import com.vinci.flashsale.storage.biz.entity.StorageDO;
import com.vinci.flashsale.storage.biz.mapper.StorageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Exception.class)
    public void decrease(String commodityCode, Integer count) {
        synchronized (DefaultStorageService.class) {
            StorageDO storageDO = storageMapper.findByCommodityCode(commodityCode);
            storageDO.setCount(storageDO.getCount() - count);
            storageMapper.updateById(storageDO);
        }
    }

}
