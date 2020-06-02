package com.jtang.dashboard;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 远程调用失败
 * @author jtang
 * @date 2020/4/15 11:02
 */
@Component
public class HelloRemoteFallBack implements HelloRemote {

    @Override
    public String hello(@RequestParam(value = "name") String name) {
        return "hello " + name + ", i am fallback massage";
    }
}
