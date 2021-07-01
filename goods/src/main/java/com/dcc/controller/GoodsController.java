package com.dcc.controller;

import com.dcc.domain.Goods;
import com.dcc.domain.dto.GoodsDto;
import com.dcc.service.GoodsService;
import com.dcc.util.DomainHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author jianchun.chen
 * @date 2021/6/30 18:56
 *     <p>
 */
@RequestMapping("/api/goods")
@RestController
@RequiredArgsConstructor
public class GoodsController {

  private final GoodsService goodsService;

  @GetMapping("/{id}")
  public Goods getGoods(@PathVariable long id) {
    return goodsService.getById(id);
  }

  @PostMapping
  public void save(@RequestBody GoodsDto goodsDto) {
    goodsService.save(DomainHelper.dto2Entity(goodsDto, Goods.class));
  }
}
