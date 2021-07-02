package com.dcc.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @author jianchun.chen
 * @date 2021/6/30 18:32
 *     <p>
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
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
