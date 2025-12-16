package com.vinci.flashsale.storage.biz.service;

import com.vinci.flashsale.RedisLock;
import com.vinci.flashsale.common.exception.GlobalException;
import com.vinci.flashsale.common.exception.GlobalExceptionConstant;
import com.vinci.flashsale.storage.biz.constant.BizConstant;
import com.vinci.flashsale.storage.biz.dao.dataobj.StorageDO;
import com.vinci.flashsale.storage.biz.dao.mapper.StorageMapper;
import org.redisson.api.RLock;
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
    @Autowired(required = false)
    private RedisLock redisLock;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reduce(String commodityCode, Integer count) {
        RLock lock = redisLock.getLock(BizConstant.BizType.STORAGE_TYPE, BizConstant.BizId.REDUCE_ID, commodityCode);
        try {
            boolean locked = redisLock.tryLock(lock);
            if (!locked) {
                throw new GlobalException(GlobalExceptionConstant.LOCK_EXCEPTION);
            }
            StorageDO storageDO = storageMapper.findByCommodityCode(commodityCode);
            storageDO.setCount(storageDO.getCount() - count);
            storageMapper.updateById(storageDO);
        } catch (InterruptedException exception) {
            throw new GlobalException(exception);
        } finally {
            redisLock.unlock(lock);
        }
    }

}
