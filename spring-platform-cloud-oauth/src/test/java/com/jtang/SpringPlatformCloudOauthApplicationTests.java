package com.jtang;

import com.jtang.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringPlatformCloudOauthApplicationTests {

    @Autowired
    private AccountService accountService;

    @Test
    void contextLoads() {
        if(accountService == null){
            System.out.println("===============");
        }
        System.out.println(accountService.getUserDetail("admin"));

    }

}
