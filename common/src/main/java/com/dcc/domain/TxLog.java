package com.dcc.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author jianchun.chen
 * @date 2021/6/30 18:30
 *     <p>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TxLog extends BaseDomain {

  private String value;
}
