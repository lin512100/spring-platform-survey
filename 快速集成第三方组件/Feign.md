## 快速集成Feign

### 依赖包
```
<dependency>
    <groupId>com.jtang</groupId>
    <artifactId>spring-platform-cloud-feign</artifactId>
</dependency>
```

### 内部调用方式
```java
@FeignClient(value = serverName, configuration = InnerFeignRequest.class,fallback = XXX.class)
public interface UserApiService {
}
```

### 外部调用方式
```java
@FeignClient(value = serverName, configuration = OutFeignRequest.class,fallback = XXX.class)
public interface UserApiService {
}
```