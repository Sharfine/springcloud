package com.sharfine.springcloud.controller;

import com.sharfine.springcloud.client.HelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/hello")
@RestController
public class HelloController {

    @Autowired
    HelloClient helloClient;

    @GetMapping("/{name}")
    public String index(@PathVariable("name") String name) {
        return helloClient.hello(name + "!");
    }

}