package com.vinci.flashsale.gateway.filter;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author vinci
 * @version 1.0
 * @date 2025/12/16
 */
@Component
public class OrderDubboFilter extends AbstractGatewayFilterFactory<OrderDubboFilter.Config> {

    @DubboReference
    private OrderDubboService orderDubboService;

    public OrderDubboFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String orderId = exchange.getRequest()
                    .getQueryParams()
                    .getFirst("orderId");

            return Mono.fromCallable(() ->
                            orderDubboService.getOrder(orderId)
                    )
                    .subscribeOn(Schedulers.boundedElastic())
                    .flatMap(order -> {
                        byte[] body = JSON.toJSONBytes(order);
                        ServerHttpResponse response = exchange.getResponse();
                        response.getHeaders()
                                .setContentType(MediaType.APPLICATION_JSON);
                        return response.writeWith(
                                Mono.just(response.bufferFactory().wrap(body))
                        );
                    });
        };
    }

    public static class Config {
    }
}
