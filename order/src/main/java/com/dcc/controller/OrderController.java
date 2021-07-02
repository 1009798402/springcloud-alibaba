package com.dcc.controller;

import com.dcc.domain.Goods;
import com.dcc.domain.Order;
import com.dcc.service.GoodsService;
import com.dcc.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * com.dcc.order 前端控制器
 *
 * @author jianchun.chen
 * @since 2021-07-01
 */
@Slf4j
@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;
  private final GoodsService goodsService;

  @GetMapping("/{id}")
  public Order getOrder(@PathVariable long id) {
    return orderService.getById(id);
  }

  @PostMapping("/goods/{goodsId}")
  public void save(@PathVariable long goodsId) {

    Goods goods = goodsService.getById(goodsId);

    Assert.notNull(goods, "goods不能为空");
    orderService.save(
        Order.builder()
            .goodsId(goods.getId())
            .goodsName(goods.getGoodsName())
            .number(1)
            .price(goods.getPrice())
            .userId(1L)
            .username("test")
            .build());
  }
}
