package com.alibaba.interaction.elephant.rpc.network;

import com.alibaba.fastjson.JSON;
import com.alibaba.interaction.elephant.netty.ChannelInboundParamHandler;
import io.netty.channel.ChannelHandlerContext;

public class RequestParamHandler extends ChannelInboundParamHandler<EleRequest> {


    @Override
    public void onChannelRead(ChannelHandlerContext ctx, EleRequest msg) {


    }


    protected void sendResponse(ChannelHandlerContext ctx, EleResponse response) {


        ctx.writeAndFlush(JSON.toJSON(response));
    }
}
