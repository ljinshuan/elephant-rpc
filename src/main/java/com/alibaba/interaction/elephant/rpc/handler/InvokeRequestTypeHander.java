package com.alibaba.interaction.elephant.rpc.handler;

import com.alibaba.interaction.elephant.rpc.RpcContext;
import com.alibaba.interaction.elephant.rpc.concurrent.DefaultListenableFuture;
import com.alibaba.interaction.elephant.rpc.meta.RpcRequestMeta;
import com.alibaba.interaction.elephant.rpc.meta.RpcResult;
import com.alibaba.interaction.elephant.rpc.network.EleRequest;
import com.alibaba.interaction.elephant.rpc.network.EleResponse;
import com.alibaba.interaction.elephant.rpc.provider.ServiceProvider;
import com.alibaba.interaction.elephant.utils.ThreadUtils;
import lombok.extern.slf4j.Slf4j;


/**
 * 方法调用处理器
 */
@Slf4j
public class InvokeRequestTypeHander implements RequestTypeHandler {
    @Override
    public DefaultListenableFuture<EleResponse> handle(RpcContext rpcContext, EleRequest request) {


        DefaultListenableFuture<EleResponse> responseFuture = DefaultListenableFuture.create();


        RpcRequestMeta serviceRequestMeta = request.getServiceRequestMeta();

        ThreadUtils.runAsyncNoWait(() -> {

            Object invoke = ServiceProvider.invoke(rpcContext, serviceRequestMeta);

            EleResponse response = new EleResponse();
            response.setSessionId(serviceRequestMeta.getSessionId());
            response.setRequestId(serviceRequestMeta.getRequestId());
            RpcResult rpcResult = new RpcResult();
            rpcResult.setData(invoke);
            response.setRpcResult(rpcResult);

            responseFuture.set(response);
            return responseFuture;
        });

        return responseFuture;
    }
}
