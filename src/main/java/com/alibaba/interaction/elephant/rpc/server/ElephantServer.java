package com.alibaba.interaction.elephant.rpc.server;

import com.alibaba.interaction.elephant.netty.FastJsonObjectDecoder;
import com.alibaba.interaction.elephant.netty.FastJsonObjectEncoder;
import com.alibaba.interaction.elephant.netty.NettyServer;
import com.alibaba.interaction.elephant.rpc.network.ProtocolParamHandler;
import com.google.common.collect.Lists;
import io.netty.channel.ChannelFuture;
import io.netty.handler.codec.json.JsonObjectDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;


@Slf4j
public class ElephantServer {


    private int port;


    private boolean running = false;

    private ChannelFuture boostChannel;

    public ElephantServer(int port) {
        this.port = port;
    }

    public void startListen() throws InterruptedException {

        if (running) {
            log.warn("the server is running do not start again...");
            return;
        }
        ProtocolParamHandler protocalParamHandler = new ServerParamHandler();
        protocalParamHandler.setRequestParamHandler(new EleServerRequestHandler());
        protocalParamHandler.setResponseParamHandler(new EleServerResponseHandler());

        ChannelFuture channelFuture = NettyServer.startListen(port, () -> Lists.newArrayList(new FastJsonObjectEncoder(), new JsonObjectDecoder(), new FastJsonObjectDecoder(), protocalParamHandler));

        this.boostChannel = channelFuture;

        this.running = true;

        log.info("start server on port {} success...", port);

    }


    /**
     *
     */
    public void stopServer() {

        Optional.ofNullable(boostChannel).ifPresent(d -> {
            try {
                d.channel().close().sync();
            } catch (InterruptedException e) {

                log.error("close servier error ", e);

            }
            this.running = false;
        });
    }
}
