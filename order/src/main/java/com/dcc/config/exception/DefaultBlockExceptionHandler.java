package com.dcc.config.exception;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jianchun.chen
 * @date 2021/7/3 17:46
 *     <p>-----
 */
@Slf4j
public class DefaultBlockExceptionHandler {

  public static String blockHandler(BlockException e) {

    log.info("do blockHandler...e = {}", e);
    return "doBlockHandler...";
  }
}
