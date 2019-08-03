package com.alibaba.interaction.elephant.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ChannelInboundParamHandler<T> extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        this.onChannelRead(ctx, (T) msg);
    }


    /**
     * @param ctx
     * @param msg
     */
    public void onChannelRead(ChannelHandlerContext ctx, T msg) {


    }

}
