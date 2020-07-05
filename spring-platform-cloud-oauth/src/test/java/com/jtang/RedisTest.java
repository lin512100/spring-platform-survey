package com.jtang;

import org.apache.tomcat.jni.Time;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void testExpire() throws InterruptedException {
        String aaaaa = "KeyTest";
        redissonClient.getBucket(aaaaa).set(50,100, TimeUnit.SECONDS);
        System.out.println("正在读取数据");
        TimeUnit.SECONDS.sleep(101);
        System.out.println("正在读取Redis数据");
        System.out.println(redissonClient.getBucket(aaaaa).get());
    }
}
