package com.alibaba.interaction.elephant.rpc.provider;

import com.alibaba.interaction.elephant.rpc.RpcContext;
import com.alibaba.interaction.elephant.rpc.meta.RpcRequestMeta;
import com.alibaba.interaction.elephant.utils.ThreadUtils;

import java.util.concurrent.ExecutorService;

public class ServiceProvider {


    public static ExecutorService RPC_PROVIDER_EXECUTOR = ThreadUtils.createExecutorService(30, "rpc-provider");

    /**
     * @param rpcContext
     * @param requestMeta
     * @return
     */
    public static Object invoke(RpcContext rpcContext, RpcRequestMeta requestMeta) {


        String data = "hell world";

        ThreadUtils.sleep(2000);

        return data;

    }
}
