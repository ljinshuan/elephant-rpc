package com.alibaba.interaction.elephant.test;

import com.alibaba.interaction.elephant.rpc.client.ElephantClient;
import com.alibaba.interaction.elephant.rpc.meta.RpcRequestMeta;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class ClientApp {


    ElephantClient elephantClient;

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    @Before
    public void init() throws InterruptedException {

        elephantClient = new ElephantClient("127.0.0.1", 8080);

        elephantClient.connect();
    }

    @Test
    public void sendTest() throws InterruptedException {


        RpcRequestMeta serviceRequestMeta = new RpcRequestMeta();
        serviceRequestMeta.setRequestId(UUID.randomUUID().toString());
        serviceRequestMeta.setServiceName("helloworld");
        serviceRequestMeta.setSessionId(UUID.randomUUID().toString());
        serviceRequestMeta.setSign("xxxx");
        serviceRequestMeta.setTimeoutMs(3000);
        serviceRequestMeta.setVersion("1.0.0");

        Object object = elephantClient.invoke(serviceRequestMeta);


        log.info("rpc data :{} ", object);
        countDownLatch.await();
    }


}
