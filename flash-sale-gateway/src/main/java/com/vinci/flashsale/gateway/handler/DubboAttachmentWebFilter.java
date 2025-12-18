package com.vinci.flashsale.gateway.handler;

import com.vinci.flashsale.common.constant.HeaderConstant;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * 将请求头中的用户 ID/请求 ID 写入 Dubbo 客户端附件，供下游 Filter 透传到 Provider。
 */
@Component
public class DubboAttachmentWebFilter implements WebFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String userId = exchange.getRequest().getHeaders().getFirst(HeaderConstant.X_USER_ID);
        String requestId = exchange.getRequest().getHeaders().getFirst(HeaderConstant.X_REQUEST_ID);

        if (userId != null) {
            RpcContext.getClientAttachment().setAttachment(HeaderConstant.USER_ID, userId);
        }
        if (requestId == null) {
            requestId = generatorRequestId();
        }
        RpcContext.getClientAttachment().setAttachment(HeaderConstant.REQUEST_ID, requestId);

        return chain.filter(exchange);
    }

    private String generatorRequestId() {
        return UUID.randomUUID().toString().replaceAll("-", "").trim();
    }

    @Override
    public int getOrder() {
        return -180;
    }
}
