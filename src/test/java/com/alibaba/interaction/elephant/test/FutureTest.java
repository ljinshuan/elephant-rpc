package com.alibaba.interaction.elephant.test;

import com.alibaba.interaction.elephant.rpc.concurrent.DefaultListenableFuture;
import com.alibaba.interaction.elephant.rpc.provider.ServiceProvider;
import com.alibaba.interaction.elephant.utils.ThreadUtils;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.Data;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.*;

public class FutureTest {


    @Test
    public void invokeTest() throws ExecutionException, InterruptedException {

        Map<String, FutureTask> futureTaskMap = Maps.newHashMap();


        ThreadUtils.runAsync(() -> {

            ThreadUtils.sleep(2000);

            FutureTask xxx = futureTaskMap.get("xxx");


            return 1;
        });


        FutureTask<Integer> send_request = ThreadUtils.createFuture(() -> {


            //发送请求
            System.out.println("send request");

            return 10;
        });
        futureTaskMap.put("xxx", send_request);
        Integer integer = send_request.get();

        System.out.println("get data " + integer);


    }

    @Test
    public void testFuture() throws ExecutionException, InterruptedException {


        DefaultListenableFuture<Integer> listenableFuture = DefaultListenableFuture.create();

        ThreadUtils.runAsync(() -> {

            ThreadUtils.sleep(3000);
            listenableFuture.set(1000);
            return 1;

        });
        listenableFuture.addListener(() -> {

            try {
                Integer integer = listenableFuture.get();
                System.out.println("get data in listener " + integer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


        }, ThreadUtils.defaultExecutor);

        Integer integer = listenableFuture.get();

        System.out.println("get result" + integer);
    }


    @Test
    public void testFuture2() {


        ExecutorService rpcProviderExecutor = ServiceProvider.RPC_PROVIDER_EXECUTOR;


    }
}
