package com.sharfine.springcloud.client;

import com.sharfine.springcloud.client.fallbackFactory.HelloFallback;
import com.sharfine.springcloud.client.fallbackFactory.HelloFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "eureka-producer", fallback = HelloFallback.class)
//@FeignClient(name = "eureka-producer", fallbackFactory = HelloFallbackFactory.class)
public interface HelloClient {
    @GetMapping("/hello/hello")
    String hello(@RequestParam(value = "name") String name);
}
