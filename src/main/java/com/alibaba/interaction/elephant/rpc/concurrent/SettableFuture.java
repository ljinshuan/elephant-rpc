package com.alibaba.interaction.elephant.rpc.concurrent;

import com.google.common.util.concurrent.ListenableFuture;

public interface SettableFuture<V> extends ListenableFuture<V> {


    /**
     * @param v
     * @return
     */
    boolean set(V v);

    /**
     * @param t
     */
    boolean setException(Throwable t);
}
