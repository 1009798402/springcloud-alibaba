package com.dcc.api.fallback;

import com.dcc.api.GoodsApi;
import com.dcc.domain.Goods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author jianchun.chen
 * @date 2021/7/3 18:17
 *     <p>----- 容错
 */
@Slf4j
@Component
public class GoodsApiFallBack implements GoodsApi {

  @Override
  public Goods getById(Long id) {

    log.info("容错开始....");
    return null;
  }

  @Override
  public String test() {
    return "容错开始....test";
  }
}
