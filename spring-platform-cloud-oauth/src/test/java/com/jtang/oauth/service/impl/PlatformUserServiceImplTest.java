package com.jtang.oauth.service.impl;

import com.jtang.oauth.model.UserJwt;
import com.jtang.oauth.service.IPlatformUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PlatformUserServiceImplTest {

    @Autowired
    private IPlatformUserService iPlatformUserService;

    @Test
    void loadUserByUsername() {
        UserJwt jtang = iPlatformUserService.loadUserByUsername("jtang");
    }
}