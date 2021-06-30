package com.dcc.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author jianchun.chen
 * @date 2021/6/30 18:18
 *     <p>
 */
@Data
public class BaseDomain {

  @TableId(type = IdType.AUTO)
  private Long id;

  @TableField(fill = FieldFill.INSERT)
  private LocalDateTime createAt;

  @TableField(fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateAt;
}
