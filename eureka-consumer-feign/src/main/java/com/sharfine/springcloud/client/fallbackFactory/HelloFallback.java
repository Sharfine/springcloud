package com.sharfine.springcloud.client.fallbackFactory;

import com.sharfine.springcloud.client.HelloClient;
import com.sharfine.springcloud.controller.HelloController;
import org.springframework.stereotype.Component;

@Component
public class HelloFallback implements HelloClient {
    @Override
    public String hello(String name) {
        return "啊啊啊啊";
    }
}
