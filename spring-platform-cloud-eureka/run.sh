# 单台注册中心
java -jar spring-platform-cloud-eureka-1.0-SNAPSHOT.jar --spring.profiles.active=dev
# 高可用注册中心
java -jar spring-platform-cloud-eureka-1.0-SNAPSHOT.jar --spring.profiles.active=prod1
java -jar spring-platform-cloud-eureka-1.0-SNAPSHOT.jar --spring.profiles.active=prod2
java -jar spring-platform-cloud-eureka-1.0-SNAPSHOT.jar --spring.profiles.active=prod3