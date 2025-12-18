package com.vinci.flashsale.storage.biz.service;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/10/28
 */
public interface StorageService {

    void reduce(Long productId, Integer quantity);

}
