package com.alibaba.interaction.elephant.rpc.handler;

import com.alibaba.interaction.elephant.rpc.RpcContext;
import com.alibaba.interaction.elephant.rpc.concurrent.DefaultListenableFuture;
import com.alibaba.interaction.elephant.rpc.network.EleRequest;
import com.alibaba.interaction.elephant.rpc.network.EleResponse;

public interface RequestTypeHandler {


    /**
     * @param rpcContext
     * @param request
     * @return
     */
    DefaultListenableFuture<EleResponse> handle(RpcContext rpcContext, EleRequest request);

}
