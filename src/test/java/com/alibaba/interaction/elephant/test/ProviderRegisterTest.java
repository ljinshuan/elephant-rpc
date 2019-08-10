package com.alibaba.interaction.elephant.test;

import com.alibaba.interaction.elephant.rpc.provider.ServiceProvider;
import com.alibaba.interaction.elephant.test.provider.HelloWorldService;
import com.alibaba.interaction.elephant.test.provider.HelloWorldServiceImpl;
import org.junit.Test;

public class ProviderRegisterTest {


    @Test
    public void test() {


        boolean b = ServiceProvider.registerProvider(HelloWorldService.class.getName(), "1.0.0.daily", new HelloWorldServiceImpl(), HelloWorldService.class);


        System.out.println(b);
    }
}
