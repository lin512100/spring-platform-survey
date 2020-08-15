## 快速集成Redisson

### 依赖

```
<!--  Redisson组件模块-->
<dependency>
    <groupId>com.jtang</groupId>
    <artifactId>spring-platform-cloud-redisson</artifactId>
</dependency>
```

### 添加第二个数据源
```
RedisHolder.addRedis(String group, RedisConfig redisConfig);
```

### Redis调用实例的方法
```
@Autowired
private RedisHolder redisHolder;

RedissonClient redissonClient = redisHolder.getInstance(int database)
RedissonClient.redissonClient = redisHolder.getGroupInstance(String group, int database) 
```

### 内置redis库默认的配置
```
spring:
  redis:
    host: 129.204.1.173
    port: 6379
    database: 0
    password: ***
```

