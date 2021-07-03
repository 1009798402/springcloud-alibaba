package com.dcc.api;

import com.dcc.domain.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author jianchun.chen
 * @date 2021/7/2 11:57
 *     <p>
 */
@FeignClient(value = "order", path = "/c")
public interface OrderApi {

  /**
   * id查订单
   *
   * @param id 订单id
   * @return 订单
   */
  @GetMapping("/{id}")
  Order getById(@PathVariable Long id);
}
