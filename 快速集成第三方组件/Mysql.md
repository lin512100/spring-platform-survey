#### 数据源配置

#### 配置类
```java
/**
 * 多数据源配置
 * @author lin512100
 * @date 2020/9/23
 */
@Configuration
@MapperScan(basePackages = "com.jtang.default.mapper",
        sqlSessionFactoryRef = CpqDataSourcesConfig.SQL_SESSION_FACTORY)
public class DefaultDataSourcesConfig {

    public static final String DATABASE_PREFIX = "spring.datasource.default.";

    public static final String DATA_SOURCE_NAME = "defaultDataSource";
    public static final String SQL_SESSION_FACTORY = "defaultSqlSessionFactory";

    /**
     * 通过配置文件创建DataSource，一个数据库对应一个DataSource
     * @param environment 环境变量，spring-boot会自动将IOC中的environment实例设置给本参数值
     * 由于IOC中有多个DataSource实例，必须给其中一个实例加上@Primary
     */
    @Primary
    @Bean(DATA_SOURCE_NAME)
    public DataSource dataSource(Environment environment) {
        return DataSourceUtil.createAtomikosDataSourceBean(DATA_SOURCE_NAME, environment, DATABASE_PREFIX);
    }

    /**
     * 通过dataSource创建SqlSessionFactory
     * 由于IOC中有多个DataSource实例，必须给其中一个实例加上@Primary
     */
    @Primary
    @Bean(name = SQL_SESSION_FACTORY)
    public SqlSessionFactory sqlSessionFactory(@Qualifier(DATA_SOURCE_NAME) DataSource dataSource) throws Exception {
        return DataSourceUtil.createSqlSessionFactory(dataSource);
    }

}
```

#### 配置文件
一个数据源一个配置类，外加一个配置文件
映射规则对应包扫描的类
```
spring:
  jta:
    # 事务管理器唯一标识符
    transaction-manager-id: nacos
  datasource:
    # Druid连接池配置。spring-boot-2默认连接池hikari不支持MysqlXADataSource
    type: com.alibaba.druid.pool.xa.DruidXADataSource
    # 最小空闲连接
    min-pool-size: 5
    # 池中最大连接数
    max-pool-size: 20
    # 设置连接在池中被自动销毁之前保留的最大秒数。 可选，默认为0（无限制）。
    max-life-time: 60
    # 返回连接前用于测试连接的SQL查询
    test-query: SELECT 1
    default:
      name: default
      url: jdbc:mysql://129.204.1.173:3306/platform-zuul?useUnicode=true&serverTimezone=UTC&useSSL=false
      username: root
      password: Jtang!14010025
    xxx:
      name: default
      url: jdbc:mysql://129.204.1.173:3306/platform-zuul?useUnicode=true&serverTimezone=UTC&useSSL=false
      username: root
      password: Jtang!14010025

```