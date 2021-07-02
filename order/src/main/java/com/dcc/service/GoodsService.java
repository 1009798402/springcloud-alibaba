package com.dcc.service;

import com.dcc.domain.Goods;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author jianchun.chen
 * @date 2021/7/2 11:18
 *     <p>
 */
@FeignClient(value = "service-goods")
public interface GoodsService {

  /**
   * id 查询
   *
   * @param id 商品id
   * @return 商品
   */
  @GetMapping("/api/goods/{id}")
  Goods getById(@PathVariable Long id);
}
