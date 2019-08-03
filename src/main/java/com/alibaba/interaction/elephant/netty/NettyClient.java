package com.alibaba.interaction.elephant.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.List;
import java.util.function.Supplier;

public class NettyClient {


    public static void startClient(String ip, int port, Supplier<List<ChannelHandler>> supplier) throws InterruptedException {

        ChannelFuture channelFuture = connect(ip, port, supplier);


        channelFuture.channel().closeFuture().sync();


    }


    public static ChannelFuture connect(String ip, int port, Supplier<List<ChannelHandler>> supplier) throws InterruptedException {


        EventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) {

                for (ChannelHandler channelHandler : supplier.get()) {
                    ch.pipeline().addLast(channelHandler);
                }


            }
        }).option(ChannelOption.SO_KEEPALIVE, true);

        ChannelFuture channelFuture = bootstrap.connect(ip, port).sync();


        return channelFuture;


    }
}
