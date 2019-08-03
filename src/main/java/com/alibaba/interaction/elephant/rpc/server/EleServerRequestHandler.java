package com.alibaba.interaction.elephant.rpc.server;

import com.alibaba.interaction.elephant.rpc.RpcContext;
import com.alibaba.interaction.elephant.rpc.concurrent.DefaultListenableFuture;
import com.alibaba.interaction.elephant.rpc.handler.RequestTypeHandler;
import com.alibaba.interaction.elephant.rpc.handler.RequestTypeHandlerRepository;
import com.alibaba.interaction.elephant.rpc.network.EleRequest;
import com.alibaba.interaction.elephant.rpc.network.EleResponse;
import com.alibaba.interaction.elephant.rpc.network.RequestParamHandler;
import com.alibaba.interaction.elephant.rpc.provider.ServiceProvider;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

@Slf4j
public class EleServerRequestHandler extends RequestParamHandler {


    @Override
    public void onChannelRead(ChannelHandlerContext ctx, EleRequest msg) {


        log.info("onChannelRead  {} {}", ctx, msg);

        RequestTypeHandler typeHandler = RequestTypeHandlerRepository.getTypeHandler(msg.getType());
        RpcContext rpcContext = new RpcContext();

        DefaultListenableFuture<EleResponse> responseFeature = typeHandler.handle(rpcContext, msg);


        responseFeature.addListener((response) -> {

            this.sendResponse(ctx, response);

        }, ServiceProvider.RPC_PROVIDER_EXECUTOR);

    }
}
