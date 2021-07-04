package com.dcc.api;

import com.dcc.api.fallback.GoodsApiFallBack;
import com.dcc.domain.Goods;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author jianchun.chen
 * @date 2021/7/2 11:18
 *     <p>
 */
@FeignClient(value = "goods", fallback = GoodsApiFallBack.class, path = "/f")
public interface GoodsApi {

  /**
   * id 查询
   *
   * @param id 商品id
   * @return 商品
   */
  @GetMapping("/{id}")
  Goods getById(@PathVariable("id") Long id);

  /** test */
  @GetMapping("/test")
  String test();
}
