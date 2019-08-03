package com.alibaba.interaction.elephant.rpc.client;

import com.alibaba.interaction.elephant.netty.FastJsonObjectDecoder;
import com.alibaba.interaction.elephant.netty.FastJsonObjectEncoder;
import com.alibaba.interaction.elephant.netty.NettyClient;
import com.alibaba.interaction.elephant.rpc.meta.RpcRequestMeta;
import com.google.common.collect.Lists;
import io.netty.channel.ChannelFuture;
import io.netty.handler.codec.json.JsonObjectDecoder;

public class ElephantClient {


    private String host;

    private int port;

    protected ChannelFuture clientChannelFuture;


    private ClientParamHandler clientParamHandler;

    public ElephantClient(String host, int port) {
        this.host = host;
        this.port = port;
    }


    public void connect() throws InterruptedException {


        clientParamHandler = new ClientParamHandler();
        clientParamHandler.setRequestParamHandler(new EleClientRequestHandler());
        clientParamHandler.setResponseParamHandler(new EleClientResponseHandler());

        ChannelFuture channelFuture = NettyClient.connect(host, port, () -> Lists.newArrayList(new FastJsonObjectEncoder(), new JsonObjectDecoder(), new FastJsonObjectDecoder(), clientParamHandler));


        clientChannelFuture = channelFuture;

    }


    public void close() throws InterruptedException {


        clientChannelFuture.channel().closeFuture().sync();
    }

    public Object invoke(RpcRequestMeta serviceRequestMeta) {


        Object invoke = clientParamHandler.invoke(serviceRequestMeta);


        return invoke;

    }
}
