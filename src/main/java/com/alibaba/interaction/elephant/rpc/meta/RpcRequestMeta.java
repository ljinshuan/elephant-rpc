package com.alibaba.interaction.elephant.rpc.meta;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 服务请求元数据
 */
@Data
public class RpcRequestMeta implements Serializable {

    private static final long serialVersionUID = 2989283671309552325L;


    /**
     * 服务名称
     */
    private String serviceName;


    /**
     * 版本
     */
    private String version;


    /**
     * 签名
     */
    private String sign;


    /**
     * sessionId
     */
    protected String sessionId;

    /**
     * 请求id
     */
    private String requestId;


    /**
     * 请求超时时间
     */
    private long timeoutMs = 3000;


    /**
     * 方法名称
     */
    private String methodName;


    /**
     * 参数
     */
    private List<Object> args;

}
