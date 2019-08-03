package com.alibaba.interaction.elephant.rpc.network;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.interaction.elephant.netty.ChannelInboundParamHandler;
import com.alibaba.interaction.elephant.rpc.ElephantConstants;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
@ChannelHandler.Sharable
public class ProtocolParamHandler extends ChannelInboundParamHandler<JSONObject> {


    private RequestParamHandler requestParamHandler;

    private ResponseParamHandler responseParamHandler;


    @Override
    public void onChannelRead(ChannelHandlerContext ctx, JSONObject msg) {


        String transportType = msg.getString("transportType");

        if (StringUtils.equalsIgnoreCase(transportType, ElephantConstants.Request)) {
            EleRequest eleRequest = msg.toJavaObject(EleRequest.class);
            requestParamHandler.onChannelRead(ctx, eleRequest);
            return;
        }

        EleResponse eleResponse = msg.toJavaObject(EleResponse.class);

        responseParamHandler.onChannelRead(ctx, eleResponse);


    }
}
