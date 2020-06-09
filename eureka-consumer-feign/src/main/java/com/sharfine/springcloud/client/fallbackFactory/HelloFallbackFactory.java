package com.sharfine.springcloud.client.fallbackFactory;

import com.sharfine.springcloud.client.HelloClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

public class HelloFallbackFactory implements FallbackFactory<HelloClient> {

    @Override
    public HelloClient create(Throwable throwable) {
        return new HelloClient() {
            @Override
            public String hello(String name) {
                return "熔断了";
            }
        };
    }
}
