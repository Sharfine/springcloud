package com.sharfine.springcloud.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RefreshScope
@RequestMapping("/hello")
public class HelloController {

    @Value("${neo.hello:error}")
    private String profile;

    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        return "Hello, " + name;
    }

    @GetMapping("/info")
    public Mono<String> hello() {
        System.out.println(profile);
        return Mono.justOrEmpty(profile);
    }
}
