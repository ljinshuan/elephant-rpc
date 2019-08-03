package com.alibaba.interaction.elephant.rpc.client;

import com.alibaba.interaction.elephant.rpc.concurrent.DefaultListenableFuture;
import com.alibaba.interaction.elephant.rpc.network.EleResponse;
import com.alibaba.interaction.elephant.rpc.network.ResponseParamHandler;
import io.netty.channel.ChannelHandlerContext;

public class EleClientResponseHandler extends ResponseParamHandler {


    @Override
    public void onChannelRead(ChannelHandlerContext ctx, EleResponse response) {


        String requestId = response.getRequestId();

        DefaultListenableFuture defaultListenableFuture = ClientParamHandler.futureMap.get(requestId);

        defaultListenableFuture.set(response.getRpcResult());

    }
}
