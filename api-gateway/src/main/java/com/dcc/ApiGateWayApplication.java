package com.dcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author jianchun.chen
 * @date 2021/7/3 21:02
 *     <p>-----
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApiGateWayApplication {

  public static void main(String[] args) {
    SpringApplication.run(ApiGateWayApplication.class);
  }
}
