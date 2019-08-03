package com.alibaba.interaction.elephant.rpc.server;

import com.alibaba.interaction.elephant.rpc.network.ProtocolParamHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class ServerParamHandler extends ProtocolParamHandler {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }
}
