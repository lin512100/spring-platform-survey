package com.jtang.account;

import com.jtang.common.service.InitUrlService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
class AccountApplicationTest {

    @Autowired
    private InitUrlService initUrlService;

    @Test
    public void test(){
        System.out.println("");
    }

}