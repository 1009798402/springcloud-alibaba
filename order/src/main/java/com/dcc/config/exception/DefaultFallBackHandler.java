package com.dcc.config.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jianchun.chen
 * @date 2021/7/3 17:46
 *     <p>-----
 */
@Slf4j
public class DefaultFallBackHandler {

  public static String fallBackHandler(Throwable t) {
    log.info("do fallBackHandler...t = {}", t);
    return "fallBackHandler...";
  }
}
