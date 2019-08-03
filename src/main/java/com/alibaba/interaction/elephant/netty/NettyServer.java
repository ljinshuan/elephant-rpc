package com.alibaba.interaction.elephant.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.List;
import java.util.function.Supplier;

public class NettyServer {


    /**
     * start a servier
     *
     * @param port
     * @param supplier
     * @throws InterruptedException
     */
    public static void startServer(int port, Supplier<List<ChannelHandler>> supplier) throws InterruptedException {


        ChannelFuture channelFuture = startListen(port, supplier);

        channelFuture.channel().closeFuture().sync();
    }


    /**
     * start a servier
     *
     * @param port
     * @param supplier
     * @return
     * @throws InterruptedException
     */
    public static ChannelFuture startListen(int port, Supplier<List<ChannelHandler>> supplier) throws InterruptedException {

        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boosGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) {

                for (ChannelHandler channelHandler : supplier.get()) {
                    ch.pipeline().addLast(channelHandler);
                }


            }
        }).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);


        ChannelFuture sync = bootstrap.bind(port).sync();


        return sync;
    }
}
