package com.dcc.controller;

import com.dcc.domain.Goods;
import com.dcc.domain.dto.GoodsDto;
import com.dcc.service.GoodsService;
import com.dcc.util.DomainHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author jianchun.chen
 * @date 2021/6/30 18:56
 *     <p>
 */
@Slf4j
@RequestMapping("/f")
@RestController
@RequiredArgsConstructor
@RefreshScope
public class GoodsController {

  private final GoodsService goodsService;

  @Value("${goods.name}")
  private String name;

  @GetMapping("/test")
  public String test() {
    log.info("test------------");
    return LocalDateTime.now() + name;
  }

  @GetMapping("/test2")
  public String test2() {
    log.info("test------------");
    return LocalDateTime.now() + "goods test22222222222";
  }

  @GetMapping("/{id}")
  public Goods getGoods(@PathVariable long id) {

    log.info("get goods id = {}", id);
    return goodsService.getById(id);
  }

  @PostMapping
  public void save(@RequestBody GoodsDto goodsDto) {
    goodsService.save(DomainHelper.dto2Entity(goodsDto, Goods.class));
  }
}
