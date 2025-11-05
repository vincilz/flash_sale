package com.vinci.flashsale.account.biz.api;

import com.vinci.flashsale.account.api.AccountApiService;
import com.vinci.flashsale.account.api.DubboAccountApiServiceTriple;
import com.vinci.flashsale.account.biz.service.AccountService;
import com.vinci.flashsale.account.dto.AccountDecreaseRequest;
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
    public CommonResponse decrease(AccountDecreaseRequest request) {
        accountService.decrease(request.getUserId(), request.getMoney());
        return CommonResponse.newBuilder().setCode(200).setMessage("OK").build();
    }

    @Override
    public CompletableFuture<CommonResponse> decreaseAsync(AccountDecreaseRequest request) {
        return CompletableFuture.supplyAsync(() -> decrease(request), ACCOUNT_EXECUTOR);
    }
}
