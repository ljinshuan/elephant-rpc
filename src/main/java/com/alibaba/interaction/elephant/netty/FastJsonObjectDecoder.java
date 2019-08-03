package com.alibaba.interaction.elephant.netty;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;
import java.util.List;

@Slf4j
public class FastJsonObjectDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {

        String s = msg.toString(Charset.defaultCharset());

        if (StringUtils.isEmpty(s)) {
            return;
        }


        JSONObject o = JSONObject.parseObject(s);
        out.add(o);


    }
}
