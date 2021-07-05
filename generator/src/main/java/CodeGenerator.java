import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.sun.javafx.PlatformUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author jianchun.chen
 * @date 2021/7/1 22:17
 *     <p>-----
 */
public class CodeGenerator {

  /** 代码生成位置 */
  public static final String PARENT_NAME = "com.dcc";

  /** modular 名字 */
  public static final String MODULAR_NAME = "generator/";

  /** 基本路径 */
  public static final String SRC_MAIN_JAVA = "src/main/java/";

  /** 作者 */
  public static final String AUTHOR = "jianchun.chen";

  /** 是否是 rest 接口 */
  private static final boolean REST_CONTROLLER_STYLE = true;

  /** 数据库 */
  public static final String JDBC_MYSQL_URL =
      "jdbc:mysql://172.16.1.22:3306/spring-cloud-albaba?useUnicode=true&characterEncoding=UTF-8&useSSL=false&useAffectedRows=true";

  public static final String JDBC_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
  public static final String JDBC_USERNAME = "douchacha";
  public static final String JDBC_PASSWORD = "Bz5jdUgzdo6HXA7x";

  public static void main(String[] args) {
    //    String moduleName = scanner("模块名");
    //    String tableName = scanner("表名");
    //    String tablePrefix = scanner("表前缀(无前缀输入#)").replaceAll("#", "");

    autoGenerator("log", "tx_log", "#");
    //    autoGenerator(moduleName, tableName, tablePrefix);
  }

  public static void autoGenerator(String moduleName, String tableName, String tablePrefix) {
    new AutoGenerator()
        .setGlobalConfig(getGlobalConfig())
        .setDataSource(getDataSourceConfig())
        .setPackageInfo(getPackageConfig(moduleName))
        .setStrategy(getStrategyConfig(tableName, tablePrefix))
        .setCfg(getInjectionConfig(moduleName))
        .setTemplate(getTemplateConfig())
        .execute();
  }

  private static String getDateTime() {
    LocalDateTime localDate = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return localDate.format(formatter);
  }

  private static InjectionConfig getInjectionConfig(final String moduleName) {
    return new InjectionConfig() {
      @Override
      public void initMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("dateTime", getDateTime());
        setMap(map);
        final String projectPath = System.getProperty("user.dir");
        List<FileOutConfig> fileOutConfigList = new ArrayList<FileOutConfig>();
        // 自定义配置会被优先输出 不生成mapper.xml

        setFileOutConfigList(fileOutConfigList);
      }
    };
  }

  private static StrategyConfig getStrategyConfig(String tableName, String tablePrefix) {
    return new StrategyConfig()
        .setNaming(NamingStrategy.underline_to_camel)
        .setColumnNaming(NamingStrategy.underline_to_camel)
        .setInclude(tableName)
        .setRestControllerStyle(REST_CONTROLLER_STYLE)
        .setEntityLombokModel(true)
        // 设置父类
        .setSuperEntityClass("BaseDomain")
        .setControllerMappingHyphenStyle(true)
        .setTablePrefix(tablePrefix + "_");
  }

  private static PackageConfig getPackageConfig(String moduleName) {
    return new PackageConfig()
        .setModuleName(moduleName)
        .setParent(PARENT_NAME)
        .setService("service")
        .setServiceImpl("service.impl")
        .setController("controller");
  }

  private static DataSourceConfig getDataSourceConfig() {
    return new DataSourceConfig()
        .setUrl(JDBC_MYSQL_URL)
        .setDriverName(JDBC_DRIVER_NAME)
        .setUsername(JDBC_USERNAME)
        .setPassword(JDBC_PASSWORD);
  }

  private static GlobalConfig getGlobalConfig() {
    String projectPath = System.getProperty("user.dir");
    String filePath = projectPath + "/" + MODULAR_NAME + SRC_MAIN_JAVA;
    if (PlatformUtil.isWindows()) {
      filePath = filePath.replaceAll("/+|\\\\+", "\\\\");
    } else {
      filePath = filePath.replaceAll("/+|\\\\+", "/");
    }
    return new GlobalConfig()
        .setOutputDir(filePath)
        .setDateType(DateType.ONLY_DATE)
        .setIdType(IdType.UUID)
        .setAuthor(AUTHOR)
        .setBaseColumnList(true)
        .setSwagger2(false)
        .setEnableCache(false)
        .setBaseResultMap(true)
        .setOpen(false);
  }

  private static TemplateConfig getTemplateConfig() {
    return new TemplateConfig()
        .setController("/templates/controller.java.vm")
        .setService("/templates/service.java.vm")
        .setServiceImpl("/templates/serviceImpl.java.vm")
        .setMapper("/templates/mapper.java.vm")
    /*.setXml("/templates/mapper.xml.vm")*/ ;
  }

  private static String scanner(String tip) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("please input " + tip + " : ");
    if (scanner.hasNext()) {
      String ipt = scanner.next();
      if (!StringUtils.isEmpty(ipt)) {
        return ipt;
      }
    }
    throw new MybatisPlusException("please input the correct " + tip + ". ");
  }
}
