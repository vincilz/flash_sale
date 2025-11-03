package com.vinci.flashsale.storage.biz.api;

import com.vinci.flashsale.storage.biz.service.StorageService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/10/28
 */
@DubboService(interfaceClass = StorageApiService.class, version = "1.0.0")
public class DefaultStorageApiService implements StorageApiService {

    @Autowired
    private StorageService storageService;

    @Override
    public void decrease(String commodityCode, int count) {
        storageService.decrease(commodityCode, count);
        throw new RuntimeException("测试异常");
    }

}
