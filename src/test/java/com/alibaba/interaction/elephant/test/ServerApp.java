package com.alibaba.interaction.elephant.test;

import com.alibaba.interaction.elephant.rpc.provider.ServiceProvider;
import com.alibaba.interaction.elephant.rpc.server.ElephantServer;
import com.alibaba.interaction.elephant.test.provider.HelloWorldService;
import com.alibaba.interaction.elephant.test.provider.HelloWorldServiceImpl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class ServerApp {


    ElephantServer elephantServer;


    CountDownLatch countDownLatch = new CountDownLatch(1);

    @Before
    public void init() throws InterruptedException {

        elephantServer = new ElephantServer(8080);

        elephantServer.startListen();
    }

    @Test
    public void sendTest() throws InterruptedException {

        boolean b = ServiceProvider.registerProvider(HelloWorldService.class.getName(), "1.0.0.daily", new HelloWorldServiceImpl(), HelloWorldService.class);

        countDownLatch.await();
    }
}
