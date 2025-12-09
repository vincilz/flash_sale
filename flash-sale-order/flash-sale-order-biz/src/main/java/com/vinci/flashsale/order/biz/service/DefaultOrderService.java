package com.vinci.flashsale.order.biz.service;

import com.vinci.flashsale.account.api.AccountApiService;
import com.vinci.flashsale.account.dto.AccountReduceRequest;
import com.vinci.flashsale.account.dto.AccountReduceSagaRequest;
import com.vinci.flashsale.order.biz.entity.OrderDO;
import com.vinci.flashsale.order.biz.entity.OrderPurchaseReqVO;
import com.vinci.flashsale.order.biz.mapper.OrderMapper;
import com.vinci.flashsale.storage.api.StorageApiService;
import com.vinci.flashsale.storage.dto.StorageReduceRequest;
import com.vinci.flashsale.storage.dto.StorageReduceSagaRequest;
import io.seata.saga.engine.StateMachineEngine;
import io.seata.saga.statelang.domain.StateMachineInstance;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/10/28
 */
@Service
public class DefaultOrderService implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @DubboReference(group = "DUBBO_GROUP", version = "1.0.0")
    private AccountApiService accountApiService;

    @DubboReference(group = "DUBBO_GROUP", version = "1.0.0")
    private StorageApiService storageApiService;

    @Autowired
    private StateMachineEngine stateMachineEngine;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void orderPurchase(OrderPurchaseReqVO reqVO) {
        // 1. 构建状态机输入参数
        Map<String, Object> startParams = new HashMap<>();

        // 构建存储扣减请求
        StorageReduceSagaRequest storageSagaRequest = StorageReduceSagaRequest.builder()
                .commodityCode(reqVO.getCommodityCode())
                .count(reqVO.getCount())
                .build();

        // 构建账户扣减请求
        AccountReduceSagaRequest accountSagaRequest = AccountReduceSagaRequest.builder()
                .userId(reqVO.getUserId())
                .money(reqVO.getMoney())
                .build();

        // 将请求放入状态机参数中（状态机定义中使用 $.[request] 来引用）
        startParams.put("storageRequest", storageSagaRequest);
        startParams.put("accountRequest", accountSagaRequest);

        // 业务键，用于补偿时识别
        String businessKey = UUID.randomUUID().toString();
        startParams.put("businessKey", businessKey);

        // 2. 启动状态机事务（最上游事务定义在这里）
        StateMachineInstance instance = stateMachineEngine.startWithBusinessKey(
                "reduceStorageAndAccount",
                "000001",
                businessKey,
                startParams);

        // 3. 检查状态机执行结果
        if (!"SU".equalsIgnoreCase(instance.getStatus().getStatusString())) {
            // 状态机执行失败或异常
            throw new RuntimeException("状态机执行失败: " + instance.getException().getMessage());
        }

        // 4. 创建订单（本地事务）
        create(reqVO.getUserId(), reqVO.getCommodityCode(), reqVO.getCount(), reqVO.getMoney());
    }

    @Override
    public void create(String userId, String commodityCode, Integer count, Integer money) {
        OrderDO orderDO = new OrderDO();
        orderDO.setUserId(userId);
        orderDO.setCommodityCode(commodityCode);
        orderDO.setCount(count);
        orderDO.setMoney(money);
        orderMapper.insert(orderDO);
    }

}
