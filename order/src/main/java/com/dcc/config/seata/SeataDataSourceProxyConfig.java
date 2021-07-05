package com.dcc.config.seata;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author jianchun.chen
 * @date 2021/7/4 21:11
 *     <p>----- stata配置
 */
@Configuration
public class SeataDataSourceProxyConfig {

  @Bean
  @ConfigurationProperties(prefix = "spring.datasource")
  public DruidDataSource druidDataSource() {
    return new DruidDataSource();
  }

  @Primary
  @Bean
  public DataSourceProxy dataSourceProxy(DruidDataSource druidDataSource) {
    return new DataSourceProxy(druidDataSource);
  }
}
