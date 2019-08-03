package com.alibaba.interaction.elephant.rpc.network;

import com.alibaba.interaction.elephant.rpc.ElephantConstants;
import com.alibaba.interaction.elephant.rpc.meta.RpcResult;
import lombok.Data;

@Data
public class EleResponse extends TransportObj {

    private static final long serialVersionUID = -7045112894866645027L;

    /**
     * 请求id
     */
    private String requestId;

    /**
     * sessionId
     */
    private String sessionId;

    /**
     * 类型
     */
    private int type;

    /**
     * 调用结果
     */
    private RpcResult rpcResult;

    @Override
    public String getTransportType() {
        return ElephantConstants.Response;
    }
}
