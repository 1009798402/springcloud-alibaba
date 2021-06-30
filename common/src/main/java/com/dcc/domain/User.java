package com.dcc.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author jianchun.chen
 * @date 2021/6/30 18:17
 *     <p>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseDomain {

  private String username;
  private String password;
  private String phone;
}
