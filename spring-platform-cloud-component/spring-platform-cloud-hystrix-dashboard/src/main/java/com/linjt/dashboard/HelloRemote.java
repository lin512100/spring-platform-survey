package com.jtang.dashboard;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author jtang
 * @date 2020/4/15 10:25
 */
@FeignClient(name= "spring-platform-cloud-producer",fallback = HelloRemoteFallBack.class)
public interface HelloRemote {

    @GetMapping(value = "/hello")
    String hello(@RequestParam(value = "name") String name);
}
