package com.dcc.filters;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author jianchun.chen
 * @date 2021/7/3 23:29
 *     <p>-----
 */
@Slf4j
@Component
public class LogGatewayFilterFactory
    extends AbstractGatewayFilterFactory<LogGatewayFilterFactory.Config> {

  public LogGatewayFilterFactory() {
    super(LogGatewayFilterFactory.Config.class);
  }

  @Override
  public List<String> shortcutFieldOrder() {
    return Collections.singletonList("log");
  }

  @Override
  public GatewayFilter apply(LogGatewayFilterFactory.Config config) {
    return (exchange, chain) -> {
      if (config.log) {
        log.info("log...");
      }

      return chain.filter(exchange);
    };
  }

  @Data
  @NoArgsConstructor
  public static class Config {

    private boolean log;
  }
}
