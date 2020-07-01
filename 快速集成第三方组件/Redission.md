## 快速集成Redission

### 依赖

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>

<dependency>
    <groupId>org.redisson</groupId>
    <artifactId>redisson</artifactId>
    <version>3.8.2</version>
</dependency>
```

### 配置

```java
@Configuration
public class RedisSessionConfig {

    @Autowired
    private RedisProperties redisProperties;

    /** 默认redis数据库 */
    private static final int DEFAULT_REDIS_DATABASE = 0;

    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        String redisUrl = String.format("redis://%s:%s",redisProperties.getHost()+"",redisProperties.getPort()+"");
        config.useSingleServer().setAddress(redisUrl).setPassword(redisProperties.getPassword());
        config.useSingleServer().setDatabase(DEFAULT_REDIS_DATABASE);
        return Redisson.create(config);
    }
}
```

### 配置
```
spring:
  redis:
    host: 129.204.1.173
    port: 6379
    database: 0
    password: ***
```

