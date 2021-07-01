package com.dcc.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author jianchun.chen
 * @date 2021/6/30 18:32
 *     <p>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("goods_order")
public class Order extends BaseDomain {

  private Long userId;
  private String username;
  private Long goodsId;
  private String goodsName;
  private Integer price;
  private Integer number;
}
