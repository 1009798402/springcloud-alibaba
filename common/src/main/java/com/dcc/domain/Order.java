package com.dcc.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author jianchun.chen
 * @date 2021/6/30 18:32
 *     <p>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Order extends BaseDomain {

  private long userId;
  private String username;
  private long goodsId;
  private String goodsName;
  private int price;
  private int number;
}
