package com.alibaba.interaction.elephant.rpc.handler;

import com.alibaba.interaction.elephant.rpc.ElephantConstants;
import com.google.common.collect.Maps;

import java.util.Map;

public class RequestTypeHandlerRepository {

    private static Map<Integer, RequestTypeHandler> typeHandlers = Maps.newHashMap();


    public static RequestTypeHandler getTypeHandler(int type) {

        return typeHandlers.get(type);
    }

    static {
        typeHandlers.put(ElephantConstants.REQUEST_TYPE_INVOKE, new InvokeRequestTypeHander());
    }
}
