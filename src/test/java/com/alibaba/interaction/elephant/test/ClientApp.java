package com.alibaba.interaction.elephant.test;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.interaction.elephant.rpc.client.ElephantClient;
import com.alibaba.interaction.elephant.rpc.consumer.ConsumerFactory;
import com.alibaba.interaction.elephant.rpc.meta.ParamMeta;
import com.alibaba.interaction.elephant.rpc.meta.RpcRequestMeta;
import com.alibaba.interaction.elephant.test.provider.HelloWorldService;
import com.alibaba.interaction.elephant.test.provider.User;
import com.google.common.collect.Lists;
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
        serviceRequestMeta.setServiceName(HelloWorldService.class.getName());
        serviceRequestMeta.setSessionId(UUID.randomUUID().toString());
        serviceRequestMeta.setSign("xxxx");
        serviceRequestMeta.setTimeoutMs(3000);
        serviceRequestMeta.setVersion("1.0.0.daily");
        serviceRequestMeta.setMethodName("sayHello");


        serviceRequestMeta.setParamMetas(Lists.newArrayList(ParamMeta.of("ljinshuan", String.class), ParamMeta.of(1234L, Long.class)));

        Object object = elephantClient.invoke(serviceRequestMeta);


        log.info("rpc data :{} ", object);
        countDownLatch.await();
    }


    @Test
    public void sendTestJSON() throws InterruptedException {


        RpcRequestMeta serviceRequestMeta = new RpcRequestMeta();
        serviceRequestMeta.setRequestId(UUID.randomUUID().toString());
        serviceRequestMeta.setServiceName(HelloWorldService.class.getName());
        serviceRequestMeta.setSessionId(UUID.randomUUID().toString());
        serviceRequestMeta.setSign("xxxx");
        serviceRequestMeta.setTimeoutMs(3000);
        serviceRequestMeta.setVersion("1.0.0.daily");
        serviceRequestMeta.setMethodName("sayHello");


        JSONObject param = new JSONObject();
        param.put("age", 10000);

        serviceRequestMeta.setParamMetas(Lists.newArrayList(ParamMeta.of("ljinshuan", String.class), ParamMeta.of(param, JSONObject.class)));

        Object object = elephantClient.invoke(serviceRequestMeta);


        log.info("rpc data :{} ", object);
        countDownLatch.await();
    }


    @Test
    public void sendTestObject() throws InterruptedException {


        RpcRequestMeta serviceRequestMeta = new RpcRequestMeta();
        serviceRequestMeta.setRequestId(UUID.randomUUID().toString());
        serviceRequestMeta.setServiceName(HelloWorldService.class.getName());
        serviceRequestMeta.setSessionId(UUID.randomUUID().toString());
        serviceRequestMeta.setSign("xxxx");
        serviceRequestMeta.setTimeoutMs(3000);
        serviceRequestMeta.setVersion("1.0.0.daily");
        serviceRequestMeta.setMethodName("sayHello");


        User user = new User();
        user.setName("ljinshuan-didi");
        user.setAge(10000L);

        serviceRequestMeta.setParamMetas(Lists.newArrayList(ParamMeta.of("ljinshuan", String.class), ParamMeta.of(user, User.class)));

        Object object = elephantClient.invoke(serviceRequestMeta);


        log.info("rpc data :{} ", object);
        countDownLatch.await();
    }


    @Test
    public void consumerTest() throws InterruptedException {


        HelloWorldService consumer = ConsumerFactory.createConsumer(HelloWorldService.class, "1.0.0.daily", elephantClient);


        User user = new User();
        user.setName("ljinshuan-didi");
        user.setAge(10000L);

        User user1 = consumer.sayHello("ljinshuan", user);


        log.info("invoke result {}", user1);

        countDownLatch.await();

    }

}
