package com.dcc.predicates;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author jianchun.chen
 * @date 2021/7/3 22:54
 *     <p>-----自定义路由条件（年龄）
 */
@Component
public class AgeRoutePredicateFactory
    extends AbstractRoutePredicateFactory<AgeRoutePredicateFactory.Config> {

  /** 构造 */
  public AgeRoutePredicateFactory() {
    super(AgeRoutePredicateFactory.Config.class);
  }

  /** 读取配置文件的参数值 赋值到配置类的属性 */
  @Override
  public List<String> shortcutFieldOrder() {

    return Arrays.asList("min", "max");
  }

  /** 逻辑 */
  @Override
  public Predicate<ServerWebExchange> apply(AgeRoutePredicateFactory.Config config) {

    Assert.isTrue(config.getMin() <= config.getMax(), config.getMin() + "必须大于" + config.getMax());
    return (GatewayPredicate)
        serverWebExchange -> {
          // 断言逻辑
          ServerHttpRequest request = serverWebExchange.getRequest();
          return true;
        };
  }

  @Data
  @NoArgsConstructor
  public static class Config {
    private int min;
    private int max;
  }
}
