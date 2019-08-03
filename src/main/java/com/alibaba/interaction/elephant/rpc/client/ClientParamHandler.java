package com.alibaba.interaction.elephant.rpc.client;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.interaction.elephant.rpc.ElephantConstants;
import com.alibaba.interaction.elephant.rpc.concurrent.DefaultListenableFuture;
import com.alibaba.interaction.elephant.rpc.meta.RpcRequestMeta;
import com.alibaba.interaction.elephant.rpc.network.EleRequest;
import com.alibaba.interaction.elephant.rpc.network.ProtocolParamHandler;
import com.google.common.collect.Maps;
import io.netty.channel.ChannelHandlerContext;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@Data
public class ClientParamHandler extends ProtocolParamHandler {


    private ChannelHandlerContext clientChannel;

    protected static Map<String, DefaultListenableFuture> futureMap = Maps.newHashMap();


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        this.clientChannel = ctx;
    }

    @Override
    public void onChannelRead(ChannelHandlerContext ctx, JSONObject msg) {
        super.onChannelRead(ctx, msg);
    }

    /**
     * @param serviceRequestMeta
     * @return
     */
    public Object invoke(RpcRequestMeta serviceRequestMeta) {

        EleRequest request = new EleRequest();
        request.setServiceRequestMeta(serviceRequestMeta);
        request.setType(ElephantConstants.REQUEST_TYPE_INVOKE);

        sendRequest(clientChannel, request);

        DefaultListenableFuture<Object> listenableFuture = DefaultListenableFuture.create();

        futureMap.put(serviceRequestMeta.getRequestId(), listenableFuture);

        try {
            return listenableFuture.get(serviceRequestMeta.getTimeoutMs(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }


        throw new IllegalStateException();


    }


    void sendRequest(ChannelHandlerContext ctx, EleRequest eleRequest) {


        Object o = JSONObject.toJSON(eleRequest);
        ctx.writeAndFlush(o);
    }
}
