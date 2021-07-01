package com.dcc.config.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * @author jianchun.chen
 * @date 2021/6/30 23:58
 *     <p>-----
 */
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

  @Override
  public void insertFill(MetaObject metaObject) {
    log.info("insert fill");

    this.setFieldValByName("updateAt", LocalDateTime.now(), metaObject);
    this.setFieldValByName("createAt", LocalDateTime.now(), metaObject);
  }

  @Override
  public void updateFill(MetaObject metaObject) {
    log.info("update fill");
    this.setFieldValByName("updateAt", LocalDateTime.now(), metaObject);
  }
}
