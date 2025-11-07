package com.vinci.flashsale.storage.biz.api;

import com.vinci.flashsale.common.dto.CommonResponse;
import com.vinci.flashsale.storage.api.StorageApiService;
import com.vinci.flashsale.storage.biz.service.StorageService;
import com.vinci.flashsale.storage.dto.StorageReduceRequest;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/10/28
 */
@Service
@DubboService(interfaceClass = StorageApiService.class, version = "1.0.0")
public class DefaultStorageApiService implements StorageApiService {

    private static final Executor STORAGE_EXECUTOR = Executors.newFixedThreadPool(8);

    @Autowired
    private StorageService storageService;

    @Override
    public CommonResponse reduce(StorageReduceRequest request) {
        storageService.reduce(request.getCommodityCode(), request.getCount());
        return CommonResponse.newBuilder().setCode(200).setMessage("OK").build();
    }

    @Override
    public CompletableFuture<CommonResponse> reduceAsync(StorageReduceRequest request) {
        return CompletableFuture.supplyAsync(() -> reduce(request), STORAGE_EXECUTOR);
    }

    @Override
    public CommonResponse compensateReduce(StorageReduceRequest request) {
        return null;
    }

    @Override
    public CompletableFuture<CommonResponse> compensateReduceAsync(StorageReduceRequest request) {
        return null;
    }
}
