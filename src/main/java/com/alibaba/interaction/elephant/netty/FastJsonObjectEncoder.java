package com.alibaba.interaction.elephant.netty;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.List;

public class FastJsonObjectEncoder extends MessageToMessageEncoder<JSONObject> {
    @Override
    protected void encode(ChannelHandlerContext ctx, JSONObject msg, List<Object> out) throws Exception {


        if (msg == null) {
            return;
        }
        ByteBuf byteBuf = ByteBufUtil.encodeString(ctx.alloc(), CharBuffer.wrap(msg.toJSONString()), Charset.defaultCharset());

        out.add(byteBuf);

    }
}
