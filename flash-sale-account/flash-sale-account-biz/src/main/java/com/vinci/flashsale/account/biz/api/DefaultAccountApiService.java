package com.vinci.flashsale.account.biz.api;

import com.vinci.flashsale.account.api.AccountApiService;
import com.vinci.flashsale.account.biz.service.AccountService;
import com.vinci.flashsale.account.dto.AccountReduceRequest;
import com.vinci.flashsale.common.dto.CommonResponse;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author vinci
 * @date 2025/10/28
 * @version 1.0
 * 
 */
@Service
@DubboService(interfaceClass = AccountApiService.class, version = "1.0.0")
public class DefaultAccountApiService implements AccountApiService {

    private static final Executor ACCOUNT_EXECUTOR = Executors.newFixedThreadPool(8);

    @Autowired
    private AccountService accountService;

    @Override
    public CommonResponse reduce(AccountReduceRequest request) {
        accountService.reduce(request.getUserId(), request.getMoney());
        return CommonResponse.newBuilder().setCode(200).setMessage("OK").build();
    }

    @Override
    public CompletableFuture<CommonResponse> reduceAsync(AccountReduceRequest request) {
        return CompletableFuture.supplyAsync(() -> reduce(request), ACCOUNT_EXECUTOR);
    }

    @Override
    public CommonResponse compensateReduce(AccountReduceRequest request) {
        return null;
    }

    @Override
    public CompletableFuture<CommonResponse> compensateReduceAsync(AccountReduceRequest request) {
        return null;
    }
}
