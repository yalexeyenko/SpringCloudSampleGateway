package com.example.springcloudsamplegateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class UpdateBrokerCookieGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    @Override
    public GatewayFilter apply(Object config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                return chain.filter(exchange).then(Mono.fromRunnable(() -> {

                    var request = exchange.getRequest();

                    var shouldWait = request.getHeaders().get("should_wait").get(0);
                    var uri = exchange.getRequest().getURI().toString();
                    var correlationId = request.getHeaders().get("correlation_id").get(0);

                    log.info("Request {} {} entered UpdateBrokerCookieGatewayFilterFactory", uri, correlationId);

                    if (shouldWait.equals("YES")) {
                        log.info("Request {} {} is waiting on UpdateBrokerCookieGatewayFilterFactory", uri, correlationId);
                        try {
                            Thread.sleep(20000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        log.info("Request '{}' [{}] is released on UpdateBrokerCookieGatewayFilterFactory", uri, correlationId);
                    }

                    log.info("Request '{}' [{}] leaves UpdateBrokerCookieGatewayFilterFactory", uri, correlationId);

                }));
            }
        };
    }
}
