package com.vinci.flashsale.storage.biz.api;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/10/28
 */
public interface StorageApiService {
    void decrease(String commodityCode, int count);
}
