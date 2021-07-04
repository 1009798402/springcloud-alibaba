package com.dcc.controller;

import com.dcc.domain.Goods;
import com.dcc.domain.dto.GoodsDto;
import com.dcc.service.GoodsService;
import com.dcc.util.DomainHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author jianchun.chen
 * @date 2021/6/30 18:56
 *     <p>
 */
@Slf4j
@RequestMapping("/b")
@RestController
@RequiredArgsConstructor
public class BackstageGoodsController {

  private final GoodsService goodsService;

  @GetMapping("/test")
  public String test() {
    log.info("test------------");
    Assert.isTrue(false, "错了");
    return LocalDateTime.now() + "goods test";
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
