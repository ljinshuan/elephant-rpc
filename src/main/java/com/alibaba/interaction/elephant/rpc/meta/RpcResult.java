package com.alibaba.interaction.elephant.rpc.meta;

import lombok.Data;

import java.io.Serializable;

@Data
public class RpcResult implements Serializable {

    private static final long serialVersionUID = 6430607906696043029L;


    private Object data;


    private Throwable exception;
}
