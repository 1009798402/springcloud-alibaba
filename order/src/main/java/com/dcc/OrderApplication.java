package com.dcc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author jianchun.chen
 * @date 2021/6/30 18:46
 *     <p>
 */
@SpringBootApplication
@MapperScan("com.dcc.mapper")
@EnableFeignClients
public class OrderApplication {

  public static void main(String[] args) {
    SpringApplication.run(OrderApplication.class);
  }
}
