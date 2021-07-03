package com.dcc.controller;

import com.dcc.domain.Goods;
import com.dcc.domain.dto.GoodsDto;
import com.dcc.service.GoodsService;
import com.dcc.util.DomainHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author jianchun.chen
 * @date 2021/6/30 18:56
 *     <p>
 */
@Slf4j
@RequestMapping("/api/goods")
@RestController
@RequiredArgsConstructor
public class GoodsController {

  private final GoodsService goodsService;

  int i = 0;

  @GetMapping("/test")
  public String test() {

    i++;
    if (i % 3 == 0) {
      i = 1 / 0;
    }

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
