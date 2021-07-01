package dcc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jianchun.chen
 * @date 2021/6/30 18:46
 *     <p>
 */
@SpringBootApplication
@MapperScan("com.dcc.mapper")
public class GoodsApplication {

  public static void main(String[] args) {
    SpringApplication.run(GoodsApplication.class);
  }
}
