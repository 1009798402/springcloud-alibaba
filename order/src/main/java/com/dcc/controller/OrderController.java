package com.dcc.controller;

import com.dcc.domain.Order;
import com.dcc.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * com.dcc.order 前端控制器
 *
 * @author jianchun.chen
 * @since 2021-07-01
 */
@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @GetMapping("/{id}")
  public Order getOrder(@PathVariable long id) {
    return orderService.getById(id);
  }

  @PostMapping
  public void save(@RequestBody Order order) {
    orderService.save(order);
  }
}
