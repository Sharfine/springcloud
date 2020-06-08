package com.sharfine.springcloud.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "eureka-producer")
public interface HelloClient {
    @GetMapping("/hello/")
    String hello(@RequestParam(value = "name") String name);
}
