package com.dcc.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dcc.config.exception.DefaultBlockExceptionHandler;
import com.dcc.config.exception.DefaultFallBackHandler;
import com.dcc.domain.Order;
import com.dcc.mapper.OrderMapper;
import com.dcc.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;

/**
 * com.dcc.order 服务实现类
 *
 * @author jianchun.chen
 * @since 2021-07-01
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

  private final RocketMQTemplate rocketMQTemplate;

  int i = 0;

  @SentinelResource(
      value = "doOrder",
      blockHandlerClass = DefaultBlockExceptionHandler.class,
      blockHandler = "blockHandler",
      fallbackClass = DefaultFallBackHandler.class,
      fallback = "fallBackHandler")
  @Override
  public String doOrder() {
    i++;
    if (i % 3 == 0) {
      i = 1 / 0;
    }
    log.info("do order...");

    // 发送消息 topic  消息内容
    // 1. 可靠同步发送
    rocketMQTemplate.syncSend("topic", new Order());
    // 2. 可靠异步发送  3. 单项发送

    return "success";
  }

  public String doOrderBlockHandler(BlockException e) {

    // sentinel 的block异常

    log.info("doOrderBlockHandler... e = {}", e.getMessage());
    return "doOrderBlockHandler";
  }

  public String doOrderFallBack(Throwable t) {

    // 业务逻辑异常

    log.info("doOrderFallBack... t = {}", t.getMessage());
    return "doOrderFallBack";
  }
}
