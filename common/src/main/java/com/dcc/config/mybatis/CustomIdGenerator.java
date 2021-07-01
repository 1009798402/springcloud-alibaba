package com.dcc.config.mybatis;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author jianchun.chen
 * @date 2021/7/1 23:07
 *     <p>-----
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CustomIdGenerator implements IdentifierGenerator {

  @Override
  public Long nextId(Object entity) {

    return IdWorker.getId();
  }
}
