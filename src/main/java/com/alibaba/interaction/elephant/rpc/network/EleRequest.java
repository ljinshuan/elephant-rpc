package com.alibaba.interaction.elephant.rpc.network;

import com.alibaba.interaction.elephant.rpc.ElephantConstants;
import com.alibaba.interaction.elephant.rpc.meta.RpcRequestMeta;
import lombok.Data;

@Data
public class EleRequest extends TransportObj {


    private static final long serialVersionUID = 6496576287379927439L;


    /**
     * 协议版本
     */
    private String protocolVersion = "V1";


    /**
     * 请求类型  @{@link com.alibaba.interaction.elephant.rpc.ElephantConstants}
     */
    private int type;

    /**
     * 请求元数据
     */
    private RpcRequestMeta serviceRequestMeta;

    @Override
    public String getTransportType() {
        return ElephantConstants.Request;
    }
}
