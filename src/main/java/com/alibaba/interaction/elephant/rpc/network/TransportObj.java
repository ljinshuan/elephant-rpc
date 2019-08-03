package com.alibaba.interaction.elephant.rpc.network;

import java.io.Serializable;

public abstract class TransportObj implements Serializable {


    /**
     * @return
     */
    public abstract String getTransportType();

}
