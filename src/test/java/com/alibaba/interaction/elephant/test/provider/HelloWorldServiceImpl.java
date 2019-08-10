package com.alibaba.interaction.elephant.test.provider;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloWorldServiceImpl implements HelloWorldService {
    @Override
    public boolean sayHello(String name) {

        log.info("sayHello 1 name {}", name);
        return false;
    }

    @Override
    public boolean sayHello(String name, Long age) {
        log.info("sayHello 2 name {} age:{}", name, age);
        return false;
    }

    @Override
    public JSONObject sayHello(String name, JSONObject data) {

        log.info("sayHello 4 name {} data:{}", name, data);

        data.put("hahha", "hello");

        return data;
    }

    @Override
    public User sayHello(String name, User user) {

        log.info("sayHello 5 name {} data:{}", name, user);

        user.setName(user.getName() + "hahaha");

        return user;
    }

    @Override
    public boolean sayHello() {
        log.info("sayHello 3");
        return false;
    }
}
