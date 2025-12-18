package com.vinci.flashsale.storage.biz.service;

import com.vinci.flashsale.constant.RedisConstant;
import com.vinci.flashsale.common.exception.GlobalException;
import com.vinci.flashsale.common.exception.GlobalExceptionConstant;
import com.vinci.flashsale.storage.biz.constant.BizConstant;
import com.vinci.flashsale.storage.biz.dao.dataobj.StorageDO;
import com.vinci.flashsale.storage.biz.dao.mapper.StorageMapper;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
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
    @Autowired
    private RedissonClient redissonClient;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reduce(Long productId, Integer quantity) {
        RLock lock = redissonClient.getLock(String.format(
                RedisConstant.LOCK_KEY_PATTERN,
                BizConstant.BizType.STORAGE_TYPE,
                BizConstant.BizId.REDUCE_ID,
                productId.toString())
        );

        try {
            boolean locked = lock.tryLock();
            if (!locked) {
                throw new GlobalException(GlobalExceptionConstant.LOCK_EXCEPTION);
            }
            StorageDO storageDO = storageMapper.findByCommodityCode(productId);
            storageDO.setQuantity(storageDO.getQuantity() - quantity);
            storageMapper.updateById(storageDO);
        } finally {
            lock.unlock();
        }
    }

}
