package com.alibaba.interaction.elephant.test;

import com.alibaba.interaction.elephant.rpc.server.ElephantServer;
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


        countDownLatch.await();
    }
}
