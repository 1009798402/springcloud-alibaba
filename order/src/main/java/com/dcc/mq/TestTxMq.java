package com.dcc.mq;

import com.dcc.domain.TxLog;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

/**
 * @author jianchun.chen
 * @date 2021/7/4 18:53
 *     <p>-----
 */
@RequiredArgsConstructor
@RocketMQTransactionListener
public class TestTxMq implements RocketMQLocalTransactionListener {

  private final TestProduceMq testProduceMq;
  private final TxLog txLog;

  @Override
  public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
    // 执行本地事务
    Long txLogId = (Long) message.getHeaders().get("txLogId");
    try {
      testProduceMq.testDoTx(txLogId);
      return RocketMQLocalTransactionState.COMMIT;
    } catch (Exception e) {
      e.printStackTrace();
      return RocketMQLocalTransactionState.ROLLBACK;
    }
  }

  @Override
  public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
    // 消息回查
    // todo 查询log
    if (txLog != null) {
      return RocketMQLocalTransactionState.COMMIT;
    } else {
      return RocketMQLocalTransactionState.ROLLBACK;
    }
  }
}
