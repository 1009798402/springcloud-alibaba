package dcc.controller;

import com.dcc.domain.Goods;
import com.dcc.util.DomainHelper;
import dcc.domain.dto.GoodsDto;
import dcc.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author jianchun.chen
 * @date 2021/6/30 18:56
 *     <p>
 */
@RestController
@RequiredArgsConstructor
public class GoodsController {

  private final GoodsService goodsService;

  @GetMapping("/{id}")
  public Goods getGoods(@PathVariable long id) {
    return goodsService.getById(id);
  }

  @PostMapping
  public void save(@RequestBody GoodsDto userDto) {
    goodsService.save(DomainHelper.dto2Entity(userDto, Goods.class));
  }
}
