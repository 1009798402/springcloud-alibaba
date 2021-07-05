package com.dcc.mq;

import com.dcc.domain.Order;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author jianchun.chen
 * @date 2021/7/4 18:19
 *     <p>----- MQ消息消费 消费消息 consumerGroup 消费者组名 topic topic
 */
@Component
@RocketMQMessageListener(
    consumerGroup = "user",
    topic = "topic",
    consumeMode = ConsumeMode.ORDERLY, // 消费模式  CONCURRENTLY无序消费   ORDERLY顺序消费
    messageModel = MessageModel.CLUSTERING // 消费模式 BROADCASTING广播 CLUSTERING集群 默认集群
    )
public class TestConsumMQ implements RocketMQListener<Order> {

  @Override
  public void onMessage(Order order) {}
}
