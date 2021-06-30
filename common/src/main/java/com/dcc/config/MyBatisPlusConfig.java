package com.dcc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jianchun.chen
 * @date 2021/1/14 19:42
 *     <p>-----
 */
@Configuration
public class MyBatisPlusConfig {

  @Bean
  public MyMetaObjectHandler baseHandler() {
    return new MyMetaObjectHandler();
  }
}
