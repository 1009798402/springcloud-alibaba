package com.dcc.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author jianchun.chen
 * @date 2021/7/4 0:07
 *     <p>----- 自定义全局鉴权
 */
@Slf4j
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

  public static final String TOKEN_NAME = "Authorization";

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

    HttpHeaders httpHeaders = exchange.getRequest().getHeaders();
    log.info("httpHeaders = {}", httpHeaders);
    List<String> headers = httpHeaders.get(TOKEN_NAME);
    log.info("headers = {}", headers);

    if (CollectionUtils.isEmpty(headers)) {
      exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
      return exchange.getResponse().setComplete();
    } else {
      return chain.filter(exchange);
    }
  }

  @Override
  public int getOrder() {
    return 0;
  }
}
