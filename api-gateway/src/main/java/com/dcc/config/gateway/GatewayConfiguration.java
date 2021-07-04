package com.dcc.config.gateway;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author jianchun.chen
 * @date 2021/7/4 10:26
 *     <p>----- gateway网关限流
 */
@Configuration
public class GatewayConfiguration {

  private final List<ViewResolver> viewResolvers;
  private final ServerCodecConfigurer serverCodecConfigurer;

  public GatewayConfiguration(
      ObjectProvider<List<ViewResolver>> provider, ServerCodecConfigurer serverCodecConfigurer) {
    this.viewResolvers = provider.getIfAvailable(Collections::emptyList);
    this.serverCodecConfigurer = serverCodecConfigurer;
  }

  /** 初始化一个限流过滤器. */
  @Bean
  @Order(Ordered.HIGHEST_PRECEDENCE)
  public GlobalFilter sentinelGatewayFilter() {
    return new SentinelGatewayFilter();
  }

  /** 配置限流的异常处理器. */
  @Bean
  @Order(Ordered.HIGHEST_PRECEDENCE)
  public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
    return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
  }

  /** 自定义限流异常处理 */
  @PostConstruct
  public void initBlockHandlers() {

    // 做ip,手机号啥的限流
    GatewayCallbackManager.setBlockHandler(
        (serverWebExchange, throwable) ->
            ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue("请稍后再试")));
  }

  /** 配置规则. */
  @PostConstruct
  public void initGatewayRules() {
    // 根据ruleId进行限流
    //    Set<GatewayFlowRule> rules = new HashSet<>();
    //    // 路由id , 阈值 , 时间（秒）
    //    rules.add(new GatewayFlowRule("goods_route").setCount(1).setIntervalSec(1));
    //    GatewayRuleManager.loadRules(rules);

    // 根据api分组限流
    Set<GatewayFlowRule> rules = new HashSet<>();
    // 路由id , 阈值 , 时间（秒）
    //    rules.add(new GatewayFlowRule("api_front").setCount(1).setIntervalSec(1));
    //    rules.add(new GatewayFlowRule("api_backstage").setCount(1).setIntervalSec(2));
    GatewayRuleManager.loadRules(rules);
  }

  /** 根据api组进行限流 */
  @PostConstruct
  private void initCustomizedApis() {
    Set<ApiDefinition> definitions = new HashSet<>();
    ApiDefinition apiFront =
        new ApiDefinition("api_front")
            .setPredicateItems(
                new HashSet<>() {
                  {
                    add(
                        new ApiPathPredicateItem()
                            .setPattern("/*/f/**")
                            .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
                  }
                });

    ApiDefinition apiBackstage =
        new ApiDefinition("api_backstage")
            .setPredicateItems(
                new HashSet<>() {
                  {
                    add(
                        new ApiPathPredicateItem()
                            .setPattern("/**/b/**")
                            .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
                  }
                });

    definitions.add(apiFront);
    definitions.add(apiBackstage);
    GatewayApiDefinitionManager.loadApiDefinitions(definitions);
  }
}
