package com.dcc.mq;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.dcc.domain.Order;
import com.dcc.domain.TxLog;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jianchun.chen
 * @date 2021/7/4 18:25
 *     <p>-----
 */
@RequiredArgsConstructor
public class TestProduceMq {

  private final RocketMQTemplate rocketMQTemplate;
  private final TxLog txLog;

  public void testSyncSend() {
    /*
    同步消息
    1.topic,
    2.消息内容,
    3.超时时间
    */
    SendResult result = rocketMQTemplate.syncSend("topic", new Order(), 3000);

    /* 同步消息
    1.topic:tag,
    2.消息内容,
    3.超时时间
    */
    SendResult result2 = rocketMQTemplate.syncSend("topic:tag", new Order(), 3000);
  }

  public void testAsyncSend() {
    /*
    异步消息
    1.topic:tag,
    2.消息内容,
    3.回调方法
    */
    rocketMQTemplate.asyncSend(
        "topic:tag",
        new Order(),
        new SendCallback() {

          @Override
          public void onSuccess(SendResult sendResult) {
            // 发送成功
            System.out.println("success");
          }

          @Override
          public void onException(Throwable throwable) {
            // 失败
            System.out.println("fail");
          }
        });
  }

  public void testSendOneWay() {
    /*
    单项消息
    1.topic:tag,
    2.消息内容,
    */
    rocketMQTemplate.sendOneWay("topic:tag", new Order());
  }

  public void sendOneWayOrderly() {
    /*
    单项顺序消息
    1.topic:tag,
    2.消息内容,
    3.发送到哪个队列,
    */
    rocketMQTemplate.sendOneWayOrderly("topic:tag", new Order(), "queue");
  }

  public void createMqBefore() {
    Order order = new Order();

    Long txLogId = IdWorker.getId();
    /*
    发送半事务消息
    1.topic
    2.消息
    3.参数
     */
    TransactionSendResult transactionSendResult =
        rocketMQTemplate.sendMessageInTransaction(
            "topic",
            MessageBuilder.withPayload(order).setHeader("txLogId", txLogId).build(),
            order);
  }

  @Transactional(rollbackFor = Throwable.class)
  public void testDoTx(Long txLogId) {
    TxLog txLog = new TxLog();
    txLog.setId(txLogId);
    // todo 入库
  }
}
