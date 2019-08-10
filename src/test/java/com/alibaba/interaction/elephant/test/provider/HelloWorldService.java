package com.alibaba.interaction.elephant.test.provider;

import com.alibaba.fastjson.JSONObject;

public interface HelloWorldService {


    boolean sayHello(String name);

    boolean sayHello(String name, Long age);

    /**
     * @param name
     * @param data
     * @return
     */
    JSONObject sayHello(String name, JSONObject data);


    /**
     * @param name
     * @param user
     * @return
     */
    User sayHello(String name, User user);

    boolean sayHello();
}
